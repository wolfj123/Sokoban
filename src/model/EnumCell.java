package model;

public class EnumCell {
	public enum CellType{
		WALL, //can also be outside of board limits
		EMPTY,
		PLAYER,
		BOX,
		STORAGE
	}
}
