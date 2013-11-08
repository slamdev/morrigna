package com.github.slamm.morrigna.core;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

@SuppressWarnings("unused")
// TODO: a lot of assets seems to be unused, need to remove them
public final class Assets {

    public static class JsonValueLoader extends AsynchronousAssetLoader<JsonValue, JsonValueLoader.JsonValueParameters> {

        public static class JsonValueParameters extends AssetLoaderParameters<JsonValue> {
        }

        private JsonValue jsonValue;

        public JsonValueLoader() {
            super(new InternalFileHandleResolver());
        }

        public JsonValueLoader(FileHandleResolver resolver) {
            super(resolver);
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, JsonValueParameters parameter) {
            return null;
        }

        @Override
        public void loadAsync(AssetManager manager, String fileName, FileHandle file, JsonValueParameters parameter) {
            jsonValue = new JsonReader().parse(file);
        }

        @Override
        public JsonValue loadSync(AssetManager manager, String fileName, FileHandle file, JsonValueParameters parameter) {
            return jsonValue;
        }
    }

    public static Texture bacon;

    public static ParticleEffect chimneySmoke;

    public static Texture coalOre;

    public static Texture copperOre;

    public static Texture craftmenu;

    public static Texture crop;

    public static Texture currentItem;

    public static TextureRegion downChar_LEFT;

    public static TextureRegion downChar_RIGHT;

    public static TextureRegion downChar_STILL;

    public static TextureRegion downCreeper_LEFT;

    public static TextureRegion downCreeper_RIGHT;

    public static TextureRegion downCreeper_STILL;

    public static TextureRegion downPig_LEFT;

    public static TextureRegion downPig_RIGHT;

    public static TextureRegion downPig_STILL;

    public static TextureRegion downRobot_LEFT;

    public static TextureRegion downRobot_RIGHT;

    public static TextureRegion downRobot_STILL;

    public static TextureRegion downWolf_LEFT;

    public static TextureRegion downWolf_RIGHT;

    public static TextureRegion downWolf_STILL;

    public static Texture farm;

    public static ParticleEffect fire;

    public static Texture furnaceGUI;

    public static Texture grass;

    public static Texture inventory;

    public static Texture itemSelector;

    public static Animation leaveAnim;

    public static SkeletonData leaveSkeletonData;

    public static TextureRegion leftChar_LEFT;

    public static TextureRegion leftChar_RIGHT;

    public static TextureRegion leftChar_STILL;

    public static TextureRegion leftCreeper_LEFT;

    public static TextureRegion leftCreeper_RIGHT;

    public static TextureRegion leftCreeper_STILL;

    public static TextureRegion leftPig_LEFT;

    public static TextureRegion leftPig_RIGHT;

    public static TextureRegion leftPig_STILL;

    public static TextureRegion leftRobot_LEFT;

    public static TextureRegion leftRobot_RIGHT;

    public static TextureRegion leftRobot_STILL;

    public static TextureRegion leftWolf_LEFT;

    public static TextureRegion leftWolf_RIGHT;

    public static TextureRegion leftWolf_STILL;

    public static TextureRegion mainChar;

    public static TextureRegion mainCreeper;

    public static TextureRegion mainPig;

    public static TextureRegion mainRobot;

    public static TiledMap mainTiledMap;

    public static Texture material;

    public static Texture pointsBar;

    public static TextureRegion rightChar_LEFT;

    public static TextureRegion rightChar_RIGHT;

    public static TextureRegion rightChar_STILL;

    public static TextureRegion rightCreeper_LEFT;

    public static TextureRegion rightCreeper_RIGHT;

    public static TextureRegion rightCreeper_STILL;

    public static TextureRegion rightPig_LEFT;

    public static TextureRegion rightPig_RIGHT;

    public static TextureRegion rightPig_STILL;

    public static TextureRegion rightRobot_LEFT;

    public static TextureRegion rightRobot_RIGHT;

    public static TextureRegion rightRobot_STILL;

    public static TextureRegion rightWolf_LEFT;

    public static TextureRegion rightWolf_RIGHT;

    public static TextureRegion rightWolf_STILL;

    public static TiledMap riverHouse;

    public static Texture robotGUI;

    public static TextureRegion shopOwner;

    public static Animation toolsMasterAnim;

    public static TextureAtlas toolsMasterAtlas;

    public static SkeletonData toolsMasterData;

    public static TextureRegion tradePerson;

    public static Animation treeAnim;

    public static TextureAtlas treeAtlas;

    public static SkeletonData treeSkeletonData;

    public static TextureRegion upChar_LEFT;

    public static TextureRegion upChar_RIGHT;

    public static TextureRegion upChar_STILL;

    public static TextureRegion upCreeper_LEFT;

    public static TextureRegion upCreeper_RIGHT;

    public static TextureRegion upCreeper_STILL;

    public static TextureRegion upPig_LEFT;

    public static TextureRegion upPig_RIGHT;

    public static TextureRegion upPig_STILL;

    public static TextureRegion upRobot_LEFT;

    public static TextureRegion upRobot_RIGHT;

    public static TextureRegion upRobot_STILL;

    public static TextureRegion upWolf_LEFT;

    public static TextureRegion upWolf_RIGHT;

    public static TextureRegion upWolf_STILL;

    private static Texture bucket;

    private static Texture buttons;

    private static BitmapFont cgfFont;

    private static Texture character;

    private static Texture chatBox;

    private static Texture corn;

    private static Texture creeper;

    private static Texture dpad;

    private static Texture dpadDOWN;

    private static Texture dpadLEFT;

    private static Texture dpadRIGHT;

    private static Texture dpadUP;

    private static Texture houseIn;

    private static Texture hydration;

    private static Texture inHouse;

    private static TextureAtlas leavesAtlas;

    private static final AssetManager MANAGER = new AssetManager();

    private static Texture pig;

    private static Texture robot;

    private static Texture wolf;
    static {
        MANAGER.setLoader(TiledMap.class, new TmxMapLoader());
        MANAGER.setLoader(JsonValue.class, new JsonValueLoader());
    }

    private Assets() {
    }

    public static void load() {
        loadTextures();
        loadTextureAtlases();
        loadJsonValues();
        loadFonts();
        loadMaps();
        loadParticleEffects();
        MANAGER.finishLoading();
        initTextures();
        initTextureRegions();
        initDefaults();
        initTextureAtlases();
        initSkelettonAnimation();
        initFonts();
        initMaps();
        initParticleEffects();
    }

    private static void initDefaults() {
        mainCreeper = downCreeper_STILL;
        mainPig = downPig_STILL;
        mainRobot = downRobot_STILL;
        mainChar = downChar_STILL;
    }

    private static void initFonts() {
        cgfFont = MANAGER.get("data/cgf.fnt", BitmapFont.class);
    }

    private static void initMaps() {
        mainTiledMap = MANAGER.get("data/maps/Map.tmx", TiledMap.class);
        riverHouse = MANAGER.get("data/maps/House.tmx", TiledMap.class);
    }

    private static void initParticleEffects() {
        chimneySmoke = MANAGER.get("data/effects/smoke.p", ParticleEffect.class);
        fire = MANAGER.get("data/effects/fire.p", ParticleEffect.class);
    }

    private static void initSkelettonAnimation() {
        SkeletonJson treeJson = new SkeletonJson(treeAtlas);
        treeSkeletonData = treeJson.readSkeletonData("tree", MANAGER.get("data/tree.json", JsonValue.class));
        treeAnim = treeSkeletonData.findAnimation("tree fall");
        SkeletonJson leavesJson = new SkeletonJson(leavesAtlas);
        leaveSkeletonData = leavesJson.readSkeletonData("leavesSkel",
                MANAGER.get("data/leavesSkel.json", JsonValue.class));
        leaveAnim = leaveSkeletonData.findAnimation("animation");
        SkeletonJson toolsMasterJson = new SkeletonJson(toolsMasterAtlas);
        toolsMasterData = toolsMasterJson.readSkeletonData("toolsMaster",
                MANAGER.get("data/toolsMaster.json", JsonValue.class));
        toolsMasterAnim = toolsMasterData.findAnimation("animation");
    }

    private static void initTextureAtlases() {
        treeAtlas = MANAGER.get("data/tree.atlas", TextureAtlas.class);
        leavesAtlas = MANAGER.get("data/leaves.atlas", TextureAtlas.class);
        toolsMasterAtlas = MANAGER.get("data/toolAtlas.atlas", TextureAtlas.class);
    }

    private static void initTextureRegions() {
        shopOwner = new TextureRegion(character, 128, 192, 32, 48);
        tradePerson = new TextureRegion(character, 224, 192, 32, 48);
        // character animations
        downChar_STILL = new TextureRegion(character, 32, 0, 32, 48);
        downChar_LEFT = new TextureRegion(character, 0, 0, 32, 48);
        downChar_RIGHT = new TextureRegion(character, 64, 0, 32, 48);
        leftChar_STILL = new TextureRegion(character, 32, 48, 32, 48);
        leftChar_LEFT = new TextureRegion(character, 0, 48, 32, 48);
        leftChar_RIGHT = new TextureRegion(character, 64, 48, 32, 48);
        rightChar_STILL = new TextureRegion(character, 32, 96, 32, 48);
        rightChar_LEFT = new TextureRegion(character, 0, 96, 32, 48);
        rightChar_RIGHT = new TextureRegion(character, 64, 96, 32, 48);
        upChar_STILL = new TextureRegion(character, 32, 144, 32, 48);
        upChar_LEFT = new TextureRegion(character, 0, 144, 32, 48);
        upChar_RIGHT = new TextureRegion(character, 64, 144, 32, 48);
        // creeper animations
        downCreeper_STILL = new TextureRegion(creeper, 32, 0, 32, 48);
        downCreeper_LEFT = new TextureRegion(creeper, 0, 0, 32, 48);
        downCreeper_RIGHT = new TextureRegion(creeper, 64, 0, 32, 48);
        leftCreeper_STILL = new TextureRegion(creeper, 32, 48, 32, 48);
        leftCreeper_LEFT = new TextureRegion(creeper, 0, 48, 32, 48);
        leftCreeper_RIGHT = new TextureRegion(creeper, 64, 48, 32, 48);
        rightCreeper_STILL = new TextureRegion(creeper, 32, 96, 32, 48);
        rightCreeper_LEFT = new TextureRegion(creeper, 0, 96, 32, 48);
        rightCreeper_RIGHT = new TextureRegion(creeper, 64, 96, 32, 48);
        upCreeper_STILL = new TextureRegion(creeper, 32, 144, 32, 48);
        upCreeper_LEFT = new TextureRegion(creeper, 0, 144, 32, 48);
        upCreeper_RIGHT = new TextureRegion(creeper, 64, 144, 32, 48);
        // robot animations
        downRobot_STILL = new TextureRegion(robot, 32, 0, 32, 48);
        downRobot_LEFT = new TextureRegion(robot, 0, 0, 32, 48);
        downRobot_RIGHT = new TextureRegion(robot, 64, 0, 32, 48);
        leftRobot_STILL = new TextureRegion(robot, 32, 48, 32, 48);
        leftRobot_LEFT = new TextureRegion(robot, 0, 48, 32, 48);
        leftRobot_RIGHT = new TextureRegion(robot, 64, 48, 32, 48);
        rightRobot_STILL = new TextureRegion(robot, 32, 96, 32, 48);
        rightRobot_LEFT = new TextureRegion(robot, 0, 96, 32, 48);
        rightRobot_RIGHT = new TextureRegion(robot, 64, 96, 32, 48);
        upRobot_STILL = new TextureRegion(robot, 32, 144, 32, 48);
        upRobot_LEFT = new TextureRegion(robot, 0, 144, 32, 48);
        upRobot_RIGHT = new TextureRegion(robot, 64, 144, 32, 48);
        // pig animations
        downPig_STILL = new TextureRegion(pig, 32, 0, 32, 48);
        downPig_LEFT = new TextureRegion(pig, 0, 0, 32, 48);
        downPig_RIGHT = new TextureRegion(pig, 64, 0, 32, 48);
        leftPig_STILL = new TextureRegion(pig, 32, 48, 32, 48);
        leftPig_LEFT = new TextureRegion(pig, 0, 48, 32, 48);
        leftPig_RIGHT = new TextureRegion(pig, 64, 48, 32, 48);
        rightPig_STILL = new TextureRegion(pig, 32, 96, 32, 48);
        rightPig_LEFT = new TextureRegion(pig, 0, 96, 32, 48);
        rightPig_RIGHT = new TextureRegion(pig, 64, 96, 32, 48);
        upPig_STILL = new TextureRegion(pig, 32, 144, 32, 48);
        upPig_LEFT = new TextureRegion(pig, 0, 144, 32, 48);
        upPig_RIGHT = new TextureRegion(pig, 64, 144, 32, 48);
        // wolf animation
        downWolf_STILL = new TextureRegion(wolf, 48, 0, 48, 48);
        downWolf_LEFT = new TextureRegion(wolf, 0, 0, 48, 48);
        downWolf_RIGHT = new TextureRegion(wolf, 96, 0, 48, 48);
        rightWolf_STILL = new TextureRegion(wolf, 48, 48, 48, 48);
        rightWolf_LEFT = new TextureRegion(wolf, 0, 48, 48, 48);
        rightWolf_RIGHT = new TextureRegion(wolf, 96, 48, 48, 48);
        leftWolf_STILL = new TextureRegion(wolf, 48, 96, 48, 48);
        leftWolf_LEFT = new TextureRegion(wolf, 0, 96, 48, 48);
        leftWolf_RIGHT = new TextureRegion(wolf, 96, 96, 48, 48);
        upWolf_STILL = new TextureRegion(wolf, 48, 144, 48, 48);
        upWolf_LEFT = new TextureRegion(wolf, 0, 144, 48, 48);
        upWolf_RIGHT = new TextureRegion(wolf, 96, 144, 48, 48);
    }

    private static void initTextures() {
        bacon = MANAGER.get("data/Bacon.png", Texture.class);
        itemSelector = MANAGER.get("data/itemselector.png", Texture.class);
        crop = MANAGER.get("data/crop.png", Texture.class);
        bucket = MANAGER.get("data/bucket.png", Texture.class);
        hydration = MANAGER.get("data/hydration.png", Texture.class);
        corn = MANAGER.get("data/corn.png", Texture.class);
        character = MANAGER.get("data/characters.png", Texture.class);
        material = MANAGER.get("data/material.png", Texture.class);
        houseIn = MANAGER.get("data/inhouse.png", Texture.class);
        farm = MANAGER.get("data/lilfarm2.png", Texture.class);
        craftmenu = MANAGER.get("data/craftingMenu.png", Texture.class);
        robot = MANAGER.get("data/robot.png", Texture.class);
        robotGUI = MANAGER.get("data/robotGui.png", Texture.class);
        coalOre = MANAGER.get("data/coalOre.png", Texture.class);
        inventory = MANAGER.get("data/inventory.png", Texture.class);
        copperOre = MANAGER.get("data/copper.png", Texture.class);
        grass = MANAGER.get("data/grass.png", Texture.class);
        dpad = MANAGER.get("data/dPad.png", Texture.class);
        dpadLEFT = MANAGER.get("data/dpad_left.png", Texture.class);
        dpadRIGHT = MANAGER.get("data/dpad_right.png", Texture.class);
        dpadUP = MANAGER.get("data/dpad_up.png", Texture.class);
        dpadDOWN = MANAGER.get("data/dpad_down.png", Texture.class);
        buttons = MANAGER.get("data/buttonsGray.png", Texture.class);
        pointsBar = MANAGER.get("data/pointBar.png", Texture.class);
        currentItem = MANAGER.get("data/currentItem.png", Texture.class);
        creeper = MANAGER.get("data/creeperSprite.png", Texture.class);
        pig = MANAGER.get("data/pig.png", Texture.class);
        wolf = MANAGER.get("data/wolf.png", Texture.class);
        inHouse = MANAGER.get("data/inhouse.png", Texture.class);
        chatBox = MANAGER.get("data/chat.png", Texture.class);
        furnaceGUI = MANAGER.get("data/furnace.png", Texture.class);
    }

    private static void loadFonts() {
        String[] paths = { "data/cgf.fnt" };
        for (String path : paths) {
            MANAGER.load(path, BitmapFont.class); // "data/cgf_0.png"
        }
    }

    private static void loadJsonValues() {
        String[] paths = { "data/tree.json", "data/leavesSkel.json", "data/toolsMaster.json" };
        for (String path : paths) {
            MANAGER.load(path, JsonValue.class);
        }
    }

    private static void loadMaps() {
        String[] paths = { "data/maps/Map.tmx", "data/maps/House.tmx" };
        for (String path : paths) {
            MANAGER.load(path, TiledMap.class);
        }
    }

    private static void loadParticleEffects() {
        String[] paths = { "data/effects/smoke.p", "data/effects/fire.p" };
        for (String path : paths) {
            MANAGER.load(path, ParticleEffect.class);
        }
    }

    private static void loadTextureAtlases() {
        String[] paths = { "data/tree.atlas", "data/leaves.atlas", "data/toolAtlas.atlas" };
        for (String path : paths) {
            MANAGER.load(path, TextureAtlas.class);
        }
    }

    private static void loadTextures() {
        String[] paths = { "data/Bacon.png", "data/itemselector.png", "data/crop.png", "data/bucket.png",
                "data/hydration.png", "data/corn.png", "data/characters.png", "data/material.png", "data/inhouse.png",
                "data/lilfarm2.png", "data/craftingMenu.png", "data/robot.png", "data/robotGui.png",
                "data/coalOre.png", "data/inventory.png", "data/copper.png", "data/grass.png", "data/dPad.png",
                "data/dpad_left.png", "data/dpad_right.png", "data/dpad_up.png", "data/dpad_down.png",
                "data/buttonsGray.png", "data/pointBar.png", "data/currentItem.png", "data/creeperSprite.png",
                "data/pig.png", "data/wolf.png", "data/inhouse.png", "data/chat.png", "data/furnace.png" };
        for (String path : paths) {
            MANAGER.load(path, Texture.class);
        }
    }
}