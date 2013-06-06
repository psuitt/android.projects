package com.android.projects.games.blast.factory;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.android.projects.games.blast.beans.WorldObject;

public class ShapeFactory {

	public static Body createWorldBody(final World world, final WorldObject worldObject) {

		final BodyDef bodyDef = new BodyDef();

		bodyDef.type = worldObject.getBodyType();
		bodyDef.position.x = worldObject.getX();
		bodyDef.position.y = worldObject.getY();

		final Body body = world.createBody(bodyDef);

		final FixtureDef f = createFixture(worldObject);

		body.createFixture(f);

		return body;


	}

	private static FixtureDef createFixture(final WorldObject worldObject) {

		final FixtureDef f = new FixtureDef();

		f.shape = createShape(worldObject);
		f.density = worldObject.getDensity();
		f.friction = worldObject.getFriction();
		f.restitution = worldObject.getRestitution();

		return f;

	}

	private static Shape createShape(final WorldObject worldObject) {

		switch (worldObject.getWorldObjectType()) {

			case SQUARE:
				final PolygonShape shape = new PolygonShape();
				//shape.setAsBox(hx, hy);
				break;

			default:
				break;

		}


		return null;


	}


}
