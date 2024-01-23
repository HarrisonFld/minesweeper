package utils;

import java.util.ArrayList;

public class SpatialGrid<E> {
	
	ArrayList<E[]> grid = new ArrayList<E[]>();
	
	private int height;
	private int width;
	private int size = 0;
	
	public static final String TOP = "Top";
    public static final String BOTTOM = "Bottom";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";
    public static final String BOTTOM_LEFT = "Bottom Left";
    public static final String BOTTOM_RIGHT = "Bottom Right";
    public static final String TOP_LEFT = "Top Left";
    public static final String TOP_RIGHT = "Top Right";
			
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
	
	public int getArea() {
		
		return height * width;
		
	}
	
	public int getSize() {
		
		return size;
		
	}
	
	public E getRelativeTo(String relative, int index) { 
		
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
		
		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				elements[i] = getRelativeTo(TOP, index);
				break;
			case 1:
				elements[i] = getRelativeTo(BOTTOM, index);
				break;
			case 2:
				elements[i] = getRelativeTo(LEFT, index);
				break;
			case 3:
				elements[i] = getRelativeTo(RIGHT, index);
				break;
			case 4:
				elements[i] = getRelativeTo(BOTTOM_LEFT, index);
				break;
			case 5:
				elements[i] = getRelativeTo(BOTTOM_RIGHT, index);
				break;
			case 6:
				elements[i] = getRelativeTo(TOP_LEFT, index);
				break;
			case 7:
				elements[i] = getRelativeTo(TOP_RIGHT, index);
				break;
			}
		}
		
		return elements;
		
	}
	
	public String returnRandomDirection() {
		
		int random = (int) Math.round((Math.random() * 7));
		
		switch (random) {
		case 0:
			return TOP;
		case 1:
			return BOTTOM;
		case 2:
			return LEFT;
		case 3:
			return RIGHT;
		case 4:
			return BOTTOM_LEFT;
		case 5:
			return BOTTOM_RIGHT;
		case 6:
			return TOP_LEFT;
		case 7:
			return TOP_RIGHT;
		}
		
		return "EXCEDDED DIRECTION";
		
	}
	
	
}
