package com.kodingkingdom.educraft.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import com.kodingkingdom.educraft.Student;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.wimbli.WorldBorder.Config;
import com.wimbli.WorldBorder.CoordXZ;
import com.wimbli.WorldBorder.WorldBorder;
import com.wimbli.WorldBorder.WorldTrimTask;

public class World {
	private static String universeId = "eduCraft_universe";

	private static HashSet<World> worlds;
	
	private static int expandFactor = 2;
	private static Region worldsRegion;
	public class Region{
		public XZ minXZ;
		public XZ maxXZ;
		public Region subRegion1=null;
		public Region subRegion2=null;
		public Region(XZ MinXZ, XZ MaxXZ){
			minXZ=MinXZ;
			maxXZ=MaxXZ;}
		public Region expand(int ExpandFactor){
			Region newRegion = new Region(minXZ,maxXZ.minus(minXZ).expand(ExpandFactor));
			newRegion.insert(maxXZ.minus(minXZ));
			return newRegion;}
		public Region insert(XZ size){
			if (subRegion1==null&&subRegion2==null){
				if (size.X>maxXZ.X-minXZ.X||size.Z>maxXZ.Z-minXZ.Z) return null;
				subRegion1 = new Region(new XZ(minXZ.X,minXZ.Z+size.Z),new XZ(maxXZ.X,maxXZ.Z));
				subRegion2 = new Region(new XZ(minXZ.X+size.X,minXZ.Z),new XZ(maxXZ.X,minXZ.Z+size.Z));
				return new Region(new XZ(minXZ.X,minXZ.Z),new XZ(minXZ.X+size.X,minXZ.Z+size.Z));}
			else {
				Region sub1Insert = subRegion1.insert(size);
				if (sub1Insert != null) return sub1Insert;
				else {
					Region sub2Insert = subRegion2.insert(size);
					return sub2Insert;}}}}
	
	public String getUniverseId(){
		return universeId;}
	
	private String name;
	private XZ minXZ;
	private XZ maxXZ;
	private Position spawnPoint;

	public class XZ{
		private float X;
		private float Z;
		public XZ(float x, float z){
			X=x;Z=z;}
		public float getX(){
			return X;}
		public float getZ(){
			return Z;}
		public XZ add(XZ XZ2){
			return new XZ(this.X+XZ2.X,this.Z+XZ2.Z);}
		public XZ minus(XZ XZ2){
			return new XZ(this.X-XZ2.X,this.Z-XZ2.Z);}
		public XZ expand(int factor){
			return new XZ(this.X*factor,this.Z*factor);}}
	public class Position{
		private float X;
		private float Z;
		private float Y;
		public Position(float x, float z, float y){
			X=x;Z=z;Y=y;}
		public float getX(){
			return X;}
		public float getZ(){
			return Z;}
		public float getY(){
			return Y;}}

	private World(){}	
	public static XZ xZ(float X, float Z){
		return new World().new XZ(X,Z);}
	public static Region region(XZ MinXZ, XZ MaxXZ){
		return new World().new Region(MinXZ,MaxXZ);}
	public static Position position(float X, float Z, float Y){
		return new World().new Position(X,Z,Y);}
	public static World initializeWorld(String Name, Region MVRegion, String MVId, Position SpawnPoint){
		World world = new World();
		
		world.name=Name;
		
		Region worldRegion=worldsRegion.insert(MVRegion.maxXZ.minus(MVRegion.minXZ));
		while (worldRegion==null){
			worldsRegion=worldsRegion.expand(expandFactor);
			worldRegion=worldsRegion.insert(MVRegion.maxXZ.minus(MVRegion.minXZ));}
		
		org.bukkit.World MV = Bukkit.getServer().createWorld(new WorldCreator(MVId));
		org.bukkit.World universeMV = Bukkit.getServer().createWorld(new WorldCreator(universeId));
		
		world.copyRegion(MVRegion.minXZ,worldRegion.minXZ,
				MVRegion.maxXZ.minus(MVRegion.minXZ),
				MV,universeMV);
		
		world.minXZ=worldRegion.minXZ;
		world.maxXZ=worldRegion.maxXZ;
		world.spawnPoint=world.new Position(SpawnPoint.X+worldRegion.minXZ.minus(MVRegion.minXZ).X,
				SpawnPoint.Y,SpawnPoint.Z+worldRegion.minXZ.minus(MVRegion.minXZ).Z);
		
		worlds.add(world);
		
		return world;}

	static MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	
	HashMap<Student,HashSet<WorldItem>> studentWorldsMap;

	public final WorldItem[] createWorld(Student... worldStudents){
		try {
			WorldItem[] worldItems=initializeWorld(worldStudents);
			
			for (WorldItem worldItem : worldItems) studentWorldsMap.get(worldItem.worldStudent).add(worldItem);
			return worldItems;}
		catch (Exception e){
			throw new RuntimeException("Could not create worlds");}}
	public final WorldItem createWorld(Student worldStudent){
		try {
			WorldItem worldItem=initializeWorld(worldStudent)[0];
			
			studentWorldsMap.get(worldStudent).add(worldItem);
			return worldItem;}
		catch (Exception e){
			throw new RuntimeException("Could not create world");}}
	public final void destroyWorld(WorldItem worldItem){
		com.wimbli.WorldBorder.Config.removeBorder(worldItem.worldId);
		multiverseCore.getMVWorldManager().deleteWorld(worldItem.worldId, true, true);
		studentWorldsMap.get(worldItem.getStudent()).remove(worldItem);}	
	
	private final void copyRegion(XZ fromXZ, XZ toXZ, XZ regionSize, org.bukkit.World fromMV, org.bukkit.World toMV){
		for(float X=fromXZ.X;X<=fromXZ.X+regionSize.X;X++){
			for (float Z=fromXZ.Z;Z<=fromXZ.Z+regionSize.Z;Z++){
				Block fromBlock = fromMV.getBlockAt(new Location(fromMV, X, 0, Z));
				Block toBlock = toMV.getBlockAt(new Location(toMV, X + toXZ.X - fromXZ.X, 0, Z + toXZ.Z - fromXZ.Z));
				
				String fromBiome = fromBlock.getBiome().name();
				toBlock.setBiome(Biome.valueOf(fromBiome));
				for(int Y = 0; Y < toMV.getMaxHeight() ; Y++){
					fromBlock = fromMV.getBlockAt(new Location(fromMV, X, Y, Z));
                    int fromType = fromBlock.getTypeId();
                    byte fromData = fromBlock.getData();
					
                    toBlock = toMV.getBlockAt(new Location(toMV, X + toXZ.X - fromXZ.X, Y, Z + toXZ.Z - fromXZ.Z));
                    toBlock.setTypeIdAndData(fromType, fromData, false);
                    toBlock.setData(fromData);}}}}
	private final void trimWorld(String worldId){
		int trimFrequency = 5000;
		int trimPadding = CoordXZ.chunkToBlock(13);
		int ticks = 1, repeats = 1;
		if (trimFrequency > 20)
			repeats = trimFrequency / 20;
		else
			ticks = 20 / trimFrequency;

		WorldTrimTask trimTask = new WorldTrimTask(Bukkit.getServer(), null, worldId, trimPadding, repeats);
		if (trimTask.valid()){
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(WorldBorder.plugin, Config.trimTask, ticks, ticks);
			trimTask.setTaskID(task);}
		else
			throw new RuntimeException();}
	
	
	private final WorldItem[] initializeWorld(Student... worldStudents){
		WorldItem[] worldItems = new WorldItem[worldStudents.length]; 
		
		final org.bukkit.World universeMV = Bukkit.getServer().createWorld(new WorldCreator(universeId));
		final float widthX = (maxXZ.X-minXZ.X);
		final float lengthZ = (maxXZ.Z-minXZ.Z);		
		final float distanceX = minXZ.X + widthX/2;
		final float distanceZ = minXZ.Z + lengthZ/2;

		String masterWorldId=null;
		
		for (int i=0; i<worldStudents.length; i++){
			WorldItem worldItem = new WorldItem();
			worldItems[i] = worldItem;
			
			String worldId = String.format("eduCraft_world_%1$s_%2$s_%3$s",name,worldStudents[i].getId().toString(),UUID.randomUUID().toString());
			
			worldItem.worldId = worldId;
			worldItem.worldStudent = worldStudents[i];
					
			if (i==0){
				if (! multiverseCore.getMVWorldManager().addWorld(worldId,Environment.NORMAL,"",WorldType.FLAT,false,"",false))
						throw new RuntimeException();				

				org.bukkit.World worldMV = Bukkit.getServer().createWorld(new WorldCreator(worldId));
				copyRegion(minXZ,
						new XZ(-widthX/2,-lengthZ/2),
						new XZ(widthX,lengthZ),
						universeMV,worldMV);
				worldMV.setSpawnLocation((int)(spawnPoint.X-distanceX),(int)spawnPoint.Y,(int)(spawnPoint.Z-distanceZ));
				
				masterWorldId=worldId;				

				com.wimbli.WorldBorder.Config.setBorderCorners(worldId, -widthX, -lengthZ, widthX, lengthZ, false);
				trimWorld(masterWorldId);}
			else {
				if (! multiverseCore.getMVWorldManager().cloneWorld(masterWorldId, worldId, "")){
						throw new RuntimeException();}
				
				com.wimbli.WorldBorder.Config.setBorderCorners(worldId, -widthX, -lengthZ, widthX, lengthZ, false);}}
		
		return worldItems;}
	
	public class WorldItem{
		String worldId;
		Student worldStudent;
		
		private WorldItem(){}
				
		public Student getStudent(){
			return worldStudent;}
		public World getWorld(){
			return World.this;}}}
