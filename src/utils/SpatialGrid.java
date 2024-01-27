package utils;

import java.util.ArrayList;
import java.util.Random;

public class SpatialGrid<E> {
	
	ArrayList<E[]> grid = new ArrayList<E[]>();
	
	private int height;
	private int width;
	private int size = 0;
	
	public static enum Directions {
		
		TOP,
		BOTTOM,
		LEFT,
		RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT,
		TOP_LEFT,
		TOP_RIGHT;
		
		public static Directions returnRandomDirection() {
			
			Random r = new Random();
	        int randomNumber = r.nextInt(Directions.values().length);
			
			return Directions.values()[randomNumber];
			
		}
		
	}
			
	@SuppressWarnings("unchecked")
	public SpatialGrid(int height, int width) {
		
		this.height = height;
		this.width = height;
		
		//Handles the amount of columns
		for (int i = 0; i < this.height; i++) {
			
			grid.add((E[]) new Object[this.width]);
			
		}
		
	}
	
	//Auto Adjusts based on capacity
	public void add(E element) {
		
		grid.get((size / width))[size % width] = element;
		size++;
		
	}
	
	public boolean change(int index, E element) {
		
		try {
			grid.get(index / width)[index % width] = element;
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
	}
	
	//Gets element based on exact index
	public E get(int index) {
		
		try {
			return grid.get(index / width)[index % width];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		
	}
	
	//Gets with exact array and element
	public E get(int heightIndex, int widthIndex) {
		
		try {
			return grid.get(heightIndex)[widthIndex];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		
	}
	
	public Object[] toArray() {
		
		Object[] array = new Object[getArea()];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = grid.get(i);
		}
		
		return array;
		
		
	}
	
	public int getArea() {
		
		return height * width;
		
	}
	
	public int getSize() {
		
		return size;
		
	}
	
	public E getRelativeTo(Directions relative, int index) { 
		
		try {
			
			switch (relative) {
			case TOP:
				return grid.get((index / width) - 1)[index % width];
			case BOTTOM:
				return grid.get((index / width) + 1)[index % width];
			case LEFT:
				return grid.get(index / width)[index % width - 1];

			case RIGHT:
				return grid.get(index / width)[index % width + 1];

			case BOTTOM_LEFT:
				return grid.get((index / width) + 1)[index % width - 1];

			case BOTTOM_RIGHT:
				return grid.get((index / width) + 1)[index % width + 1];

			case TOP_LEFT:
				return grid.get((index / width) - 1)[index % width - 1];

			case TOP_RIGHT:
				return grid.get((index / width) - 1)[index % width + 1];

			}
			
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		return null;
		
	}
	
	//Will check all surroundings in a square and return an array of them
	public Object[] relativeSurroundings(int index) {
		
		Object[] elements = new Object[8];
		Directions[] directions = Directions.values();
		
		for (int i = 0; i < directions.length; i++) {
			elements[i] = getRelativeTo(directions[i], index);
		}
		
		return elements;
		
	}
	
	
	
	
}
