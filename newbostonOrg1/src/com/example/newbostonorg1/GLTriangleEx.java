package com.example.newbostonorg1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;


public class GLTriangleEx {
	private float vertices[]={
			0f,1f,	//p0
			1f,-1f,	//p1	
			-1f,-1f	//p2
	};
	private float rgbaVals[]={
			1,1,0,0.5f,
			0.25f,0,0.85f,1,
			0,1,1,1
	};
	private FloatBuffer vertBuffer;
	private FloatBuffer colorBuffer;
	
	private short[] pIndex={0,1,2};
	private ShortBuffer pBuffer;


	public GLTriangleEx() {
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
		
		ByteBuffer cBuf=ByteBuffer.allocateDirect(rgbaVals.length*4);
		cBuf.order(ByteOrder.nativeOrder());
		colorBuffer=cBuf.asFloatBuffer();
		colorBuffer.put(rgbaVals);
		colorBuffer.position(0); 
	}
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(2,GL10.GL_FLOAT, 0, vertBuffer);
		gl.glColorPointer(4,GL10.GL_FLOAT, 0,colorBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuffer);
		
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}

