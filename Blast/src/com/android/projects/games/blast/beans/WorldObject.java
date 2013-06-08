package com.android.projects.games.blast.beans;

import org.jbox2d.dynamics.BodyType;

/**
 * Used to create objects for the world.
 *
 * @author Sora
 *
 */
public class WorldObject {

	private int x = 0;
	private int y = 0;

	/** Density mass per area. */
	private float density;
	/** Friction. */
	private float friction;
	/** Bounce factor. */
	private float restitution;

	private BodyType bodyType = BodyType.STATIC;

	private WorldObjectType worldObjectType = WorldObjectType.SQUARE;

	public enum WorldObjectType {
		SQUARE
	}

	/**
	 * Constructor does nothing\.
	 */
	public WorldObject() {
	}

	public int getX() {
		return x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(final float density) {
		this.density = density;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(final float friction) {
		this.friction = friction;
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(final float restitution) {
		this.restitution = restitution;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(final BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public WorldObjectType getWorldObjectType() {
		return worldObjectType;
	}

	public void setWorldObjectType(final WorldObjectType worldObjectType) {
		this.worldObjectType = worldObjectType;
	}

}
