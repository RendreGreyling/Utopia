package engine.engineMain;

import controller.Controller;
import engine.entities.Camera;
import engine.entities.Entity;
import engine.entities.Light;
import engine.renderEngine.Loader;
import engine.renderEngine.MasterRenderer;
import engine.terrain.Selector;
import engine.terrain.Terrain;
import engine.textures.TextureAttribute;
import engine.tools.Mouse;
import engine.tools.MousePicker;
import engine.world.WorldGrid;
import model.GameModel;
import org.joml.Vector3f;

public class Handler {

    private String saveFile;
    private Loader loader = new Loader();
    private AssetLoader assets;
    private WorldGrid worldGrid;
    private Selector selector;
    private Camera camera;
    private Light light;
    private MousePicker mousePicker;
    private MasterRenderer masterRenderer;
    private GameModel gameModel;
    private Controller controller;

    private Entity entity;



    public Handler(String saveFile) {

        this.saveFile = saveFile;
        this.loader = new Loader();
        this.assets = new AssetLoader();
        this.worldGrid = new WorldGrid(loader, new TextureAttribute(loader.loadTexture("grass")));
        this.selector = new Selector(0, 0, loader, new TextureAttribute(loader.loadTexture("selector")));

        float center = Terrain.getSize() * worldGrid.getWorldSize() / 2;
        this.camera = new Camera(new Vector3f(center,100, center));
        this.light = new Light(new Vector3f(center, 1000, center), new Vector3f(1,1,1));
        this.masterRenderer = new MasterRenderer();
        this.mousePicker = new MousePicker(camera, masterRenderer.getProjectionMatrix(), worldGrid);

        this.gameModel = new GameModel(worldGrid.getWorldSize(), worldGrid.getWorldSize());
        this.gameModel.initialize();
        this.controller = new Controller(gameModel);
    }

    public void render() {
        camera.move();
        mousePicker.update();
        if (mousePicker.getCurrentTileCoords() != null) {
            selector.setX(mousePicker.getCurrentTileCoords().x);
            selector.setZ(mousePicker.getCurrentTileCoords().y);
        } else {
            selector.setX(-100);
            selector.setZ(-100);
        }

        

        Mouse.update();

        for (Terrain terrain: worldGrid.getTerrainList()) {
            masterRenderer.processTerrain(terrain);
        }

        for (Entity zone: worldGrid.getZoneList()) {
            masterRenderer.processEntities(zone);
        }

        for (Entity buildable: worldGrid.getBuildableList()) {
            masterRenderer.processEntities(buildable);
        }

        masterRenderer.render(selector, camera, light);
    }

    public void cleanUp() {
        masterRenderer.cleanUp();
        loader.cleanUp();
    }

}
