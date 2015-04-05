package com.example.newbostonorg1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;


public class GLCube {
	private float vertices[]={
	1,1,-1, //p0--top front right
	1,-1,-1,//p1--bm fr rt
	-1,-1,-1//p2 --bm fr lt
	-1,1,-1,//p3 --top fr ,lt
	1,1,1,  //p4--top behind right
	1,-1,1, //p5--bm behind rt
	-1,-1,1 //p6 --bm behind lt
	-1,1,1  //p7 --top behind lt
	};
	private FloatBuffer vertBuffer;
	private short[] pIndex={
			3,4,0,   0,4,1,  3,0,1,
			3,7,4,   7,6,4,	 7,3,6,
			3,1,2,	 1,6,2,  6,3,2,
			1,4,5,	 5,6,1,	 6,5,4
			};
	private ShortBuffer pBuffer;


	public GLCube() {
		ByteBuffer bBuf=ByteBuffer.allocateDirect(vertices.length*4);
		bBuf.order(ByteOrder.nativeOrder());
		vertBuffer=bBuf.asFloatBuffer();
		vertBuffer.put(vertices);
		vertBuffer.position(0);

		ByteBuffer pBuf=ByteBuffer.allocateDirect(pIndex.length*2);
		pBuf.order(ByteOrder.nativeOrder());
		pBuffer=pBuf.asShortBuffer();  
		pBuffer.put(pIndex);
		pBuffer.position(0);          
	}
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3,GL10.GL_FLOAT, 0, vertBuffer); 
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuffer);
		gl.glDisable(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
}

