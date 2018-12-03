package jme3test.helloworld;
  
import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class HelloJME3 extends SimpleApplication {
  
  public static void main(String args[]) {
    HelloJME3 app = new HelloJME3();
    app.start();
  }
  
  /** preparing physics State (jBullet) */
  private BulletAppState bulletAppState;
  
  /** preparing materials */
  Material wall_mat;
  Material stone_mat;
  Material floor_mat;
  
  /**   preparing physics and geometry for brics and cannon. */
  // for every figure with physics
  private RigidBodyControl    brick_phy;
  private static final Box    box;
  private RigidBodyControl    ball_phy;
  private static final Sphere sphere;
  private RigidBodyControl    floor_phy;
  private static final Box    floor;
  
  /**   sizes of bricks and wall   */
  private static final float brickLength = 0.45f;
  private static final float brickWidth  = 0.20f;
  private static final float brickHeight = 0.10f;
  
  static {
    /** cannon balls */
    sphere = new Sphere(32, 32, 0.2f, true, false);
    sphere.setTextureMode(TextureMode.Projected);
    /**  geometry of bricks */
    box = new Box(brickLength, brickHeight, brickWidth);
    box.scaleTextureCoordinates(new Vector2f(1f, .5f));
    /** geometry of floor */
    floor = new Box(10f, 0.1f, 5f);
    floor.scaleTextureCoordinates(new Vector2f(3, 6));
  }
  
  @Override
  public void simpleInitApp() {
    /** game physics */
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    //bulletAppState.setDebugEnabled(true);
  
    /** camera */
    cam.setLocation(new Vector3f(-2f, 4f, 6f));
    cam.lookAt(new Vector3f(2, 2, 0), Vector3f.UNIT_Y);
    /** adding InputManager: left click - shoot. */
    inputManager.addMapping("shoot", 
            new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addListener(actionListener, "shoot");
    /** initialising scene and physics space. */
    initMaterials();
    initWall();
    initFloor();
    initCrossHairs();
  }
  
  /** 
   */
  private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("shoot") && !keyPressed) {
        makeCannonBall();
      }
    }
  };
  
  /** initializing materials for this scene */
  public void initMaterials() {
    wall_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
    key.setGenerateMips(true);
    Texture tex = assetManager.loadTexture(key);
    wall_mat.setTexture("ColorMap", tex);
  
    stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
    key2.setGenerateMips(true);
    Texture tex2 = assetManager.loadTexture(key2);
    stone_mat.setTexture("ColorMap", tex2);
  
    floor_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key3 = new TextureKey("Textures/Terrain/Pond/Pond.jpg");
    key3.setGenerateMips(true);
    Texture tex3 = assetManager.loadTexture(key3);
    tex3.setWrap(WrapMode.Repeat);
    floor_mat.setTexture("ColorMap", tex3);
  }
  
  /**creating the floor */
  public void initFloor() {
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setMaterial(floor_mat);
    floor_geo.setLocalTranslation(0, -0.1f, 0);
    this.rootNode.attachChild(floor_geo);
    /* Создание физики пола с массой 0.0f! */
    floor_phy = new RigidBodyControl(0.0f);
    floor_geo.addControl(floor_phy);
    bulletAppState.getPhysicsSpace().add(floor_phy);
  }
  
  /** creating a wall of bricks */
  public void initWall() {
    float startpt = brickLength / 4;
    float height = 0;
    for (int j = 0; j < 15; j++) {
      for (int i = 0; i < 6; i++) {
        Vector3f vt =
         new Vector3f(i * brickLength * 2 + startpt, brickHeight + height, 0);
        makeBrick(vt);
      }
      startpt = -startpt;
      height += 2 * brickHeight;
    }
  }
  
  /** physics for every bricks. */
  public void makeBrick(Vector3f loc) {
    /** creating geometry of the brick and sticking it to the scene */
    Geometry brick_geo = new Geometry("brick", box);
    brick_geo.setMaterial(wall_mat);
    rootNode.attachChild(brick_geo);
    /** location of brick's geometry  */
    brick_geo.setLocalTranslation(loc);
    /** physics of the brick with weight > 0.0f. */
    brick_phy = new RigidBodyControl(2f);
    /** adding physics of brick to physics space. */
    brick_geo.addControl(brick_phy);
    bulletAppState.getPhysicsSpace().add(brick_phy);
  }
  
  /** method for a single cannon ball.
   */
   public void makeCannonBall() {
    /** geometry of the ball */
    Geometry ball_geo = new Geometry("cannon ball", sphere);
    ball_geo.setMaterial(stone_mat);
    rootNode.attachChild(ball_geo);
    /** location of the ball */
    ball_geo.setLocalTranslation(cam.getLocation());
    /** creating cannon ball with weight > 0.0f */
    ball_phy = new RigidBodyControl(2f);
    /**adding physics of cannon ball to physics space. */
    ball_geo.addControl(ball_phy);
    bulletAppState.getPhysicsSpace().add(ball_phy);
    /** Ускорение ядра с физикой при выстреливании. */
    ball_phy.setLinearVelocity(cam.getDirection().mult(25));
  }
  
  /** using plus size to help the gamer to shoot*/
  protected void initCrossHairs() {
    guiNode.detachAllChildren();
    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(guiFont, false);
    ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
    ch.setText("+");        
    ch.setLocalTranslation( // center
      settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
      settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
    guiNode.attachChild(ch);
  }
}