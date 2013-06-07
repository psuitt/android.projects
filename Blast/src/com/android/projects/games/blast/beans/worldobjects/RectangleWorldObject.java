package com.android.projects.games.blast.beans.worldobjects;

import com.android.projects.games.blast.beans.WorldObject;

public class RectangleWorldObject extends WorldObject {

	private float width;
	private float height;

	public RectangleWorldObject() {
		super();
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(final float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(final float height) {
		this.height = height;
	}

}
