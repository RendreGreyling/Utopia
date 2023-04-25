package engine.fontRendering;


import engine.shaders.ShaderProgram;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class FontShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/main/java/engine/fontRendering/fontVertex.glsl";
	private static final String FRAGMENT_FILE = "src/main/java/engine/fontRendering/fontFragment.glsl";

	private int location_colour;
	private int location_translation;
	
	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		location_colour = super.getUniformLocation("colour");
		location_translation = super.getUniformLocation("translation");
	}

	@Override
	protected void getAllUniformLocations() {
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0,"position");
		super.bindAttribute(1,"textureCoords");



	}
	protected  void loadColour(Vector3f colour)
	{
		super.loadVector(location_colour,colour);

	}
	protected  void loadTranslation(Vector2f translation)
	{
		super.load2DVector(location_translation,translation);
	}



}
