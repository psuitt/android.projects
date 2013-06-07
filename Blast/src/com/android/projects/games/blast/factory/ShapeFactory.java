package com.android.projects.games.blast.factory;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.android.projects.games.blast.beans.WorldObject;
import com.android.projects.games.blast.beans.worldobjects.RectangleWorldObject;

public class ShapeFactory {

	public static Body createWorldBody(final World world, final WorldObject worldObject) {

		final BodyDef bodyDef = new BodyDef();

		bodyDef.type = worldObject.getBodyType();
		bodyDef.position.x = worldObject.getX();
		bodyDef.position.y = worldObject.getY();

		final Body body = world.createBody(bodyDef);

		final FixtureDef f = createFixture(worldObject);

		body.createFixture(f);

		worldObject.setBody(body);

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

			case RECTANGLE:
				final RectangleWorldObject rectangleWorldObject = (RectangleWorldObject) worldObject;
				final PolygonShape shape = new PolygonShape();
				shape.setAsBox(rectangleWorldObject.getWidth(), rectangleWorldObject.getHeight());
				return shape;
			default:
				break;

		}


		return null;


	}


}
