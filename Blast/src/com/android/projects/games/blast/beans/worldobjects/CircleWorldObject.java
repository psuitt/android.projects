package com.android.projects.games.blast.beans.worldobjects;

import com.android.projects.games.blast.beans.WorldObject;

public class CircleWorldObject extends WorldObject {

	private float radius;

	public CircleWorldObject() {
		super();
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(final float radius) {
		this.radius = radius;
	}

}
