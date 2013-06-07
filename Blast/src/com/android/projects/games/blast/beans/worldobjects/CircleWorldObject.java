package com.android.projects.games.blast.beans.worldobjects;

import org.jbox2d.common.Vec2;

import com.android.projects.games.blast.beans.WorldObject;

public class CircleWorldObject extends WorldObject {

	private float radius;
	/** position, relative to body position. */
	private Vec2 m_p = new Vec2(0, 0);

	public CircleWorldObject() {
		super();
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(final float radius) {
		this.radius = radius;
	}

	public Vec2 getM_p() {
		return m_p;
	}

	public void setM_p(final Vec2 m_p) {
		this.m_p = m_p;
	}

}
