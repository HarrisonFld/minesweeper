package utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Constructs a grid of designated rows and columns. Each grid point can retrieve the grid point
 * 1 space over in any direction from enum class Directions.
 *
 * <P>The amount of arrays (rows) that the grid can have is specified at creation.
 * The amount of varriables that each row can have is specified at creation.
 * The grid has a total capacity of the area calculated by the specified height and width.
 *
 * @param <E>	The Generic Type to be made into a grid
 */
public class SpatialGrid<E> {

	ArrayList<E[]> grid = new ArrayList<>();

	private int height = 5;
	private int width = 5;
	private int fill = 0;

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
	/**
	 *
	 * @param height	The amount of arrays stored
	 * @param width		The maximnum amount of variables in one row
	 */
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

		grid.get((fill / width))[fill % width] = element;
		fill++;

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

	public int getFillAmount() {

		return fill;

	}

	/**
	 * Gets a grid point relative to the given grid points index and the direction specified.
	 *
	 * @param direction		The direction from the grid point.
	 * @param index			The index of the grid point.
	 * @return E
	 */
	public E getRelativeTo(Directions direction, int index) {

		try {

			switch (direction) {
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

	/**Returns an array of a grid point from every direction relative to the given grid point's index.
	 *
	 * @param index		The index of the grid point.
	 * @return Object[]
	 */
	public Object[] relativeSurroundings(int index) {

		Object[] elements = new Object[8];
		Directions[] directions = Directions.values();

		for (int i = 0; i < directions.length; i++) {
			elements[i] = getRelativeTo(directions[i], index);
		}

		return elements;

	}




}
