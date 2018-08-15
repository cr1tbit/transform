package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
interface Transformable<T extends Transformable> {
    T translate(Transformable t);

    T rotate(double angle);

    T rotateBy(double angle, Transformable t);

    SimpleMatrix getCoords();
}
