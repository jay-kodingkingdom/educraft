package com.kodingkingdom.educraft.menu;

import java.util.Collection;
import java.util.LinkedList;

public class Box<T> {
	int width,height;
	T[][] boxArray;
	
	public Box(T[][] BoxArray){
		if (BoxArray.length==0) {
			width=height=0;
			boxArray=BoxArray;}
		else{
			width=BoxArray[0].length;
			height=BoxArray.length;
			for (int i=1;i<BoxArray.length;i++){
				if (BoxArray[i].length!=width) throw new IllegalArgumentException();}
			boxArray=BoxArray;}}
	
	public int getWidth(){
		return width;}
	public int getHeight(){
		return height;}
	public T getBoxItem(int widthX, int heightY){
		return boxArray[widthX][heightY];}
	
	
	void setBoxItem(int widthX, int heightY, T item){
		boxArray[widthX][heightY]=item;}

	public T[][] asArray(){
		return boxArray.clone();}
	public Collection<T> asCollection(){
		LinkedList<T> boxCollection = new LinkedList<T>();
		for (int widthX=0;widthX<width;widthX++){
			for (int heightY=0;heightY<height;heightY++){
				boxCollection.add(getBoxItem(widthX,heightY));}}
		return boxCollection;}}
