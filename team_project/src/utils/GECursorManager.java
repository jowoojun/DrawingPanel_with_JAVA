package utils;

import java.awt.Cursor;
import java.util.ArrayList;

public class GECursorManager extends ArrayList<Cursor> {
	// GECursorManager의 생성자(각 앵커자리에 커서를 생성함)
	public GECursorManager(){
		this.add(new Cursor(Cursor.NW_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.N_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.NE_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.W_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.E_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.SW_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.S_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.SE_RESIZE_CURSOR));
		this.add(new Cursor(Cursor.HAND_CURSOR));
		this.add(new Cursor(Cursor.MOVE_CURSOR));
	}

}
