package com.cpa.project;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.cpa.project.Tiles.Tile;
import com.cpa.project.Tiles.terrainFloorTiles;

import java.util.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("CPA Project : Survivors");

        config.setWindowedMode(1600, 900);

        new Lwjgl3Application(new Survivors(), config);
    }
}




//
//package  com.cpa.project.World;
//
//
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.maps.MapProperties;
//import com.badlogic.gdx.math.Vector2;
//import com.cpa.project.Tiles.Tile;
//import com.cpa.project.Tiles.terrainFloorTiles;
//
//import java.util.*;
//        import java.util.Map;
//import java.util.stream.StreamSupport;
//
//
//class WFCNode {
//
//    String name;
//    Tile tile;
//    com.cpa.project.World.WFCConnection Top; // les connections du haut de la tile qui sont possibles
//    com.cpa.project.World.WFCConnection Bottom;
//    com.cpa.project.World.WFCConnection Left;
//    com.cpa.project.World.WFCConnection Right;
//
//    double entropy;
//
////    WFCConnection TopRight;
////    WFCConnection TopLeft;
////    WFCConnection BottomRight;
////    WFCConnection BottomLeft;
//
//
//    public WFCNode(String name, Tile tile , double entropy ) {
//        this.name = name;
//        this.tile = tile;
//        this.entropy = entropy;
//        this.Top = new com.cpa.project.World.WFCConnection();
//        this.Bottom = new com.cpa.project.World.WFCConnection();
//        this.Left = new com.cpa.project.World.WFCConnection();
//        this.Right = new com.cpa.project.World.WFCConnection();
//
////        this.TopRight = new WFCConnection();
////        this.TopLeft = new WFCConnection();
////        this.BottomRight = new WFCConnection();
////        this.BottomLeft = new WFCConnection();
//
//    }
//
//    public com.cpa.project.World.WFCNode addTop(com.cpa.project.World.WFCNode node){
//        Top.addNode(node);
//        node.Bottom.addNode(this);
//        return this;
//    }
//
//    public com.cpa.project.World.WFCNode addBottom(com.cpa.project.World.WFCNode node){
//        Bottom.addNode(node);
//        node.Top.addNode(this);
//        return this;
//    }
//
//    public com.cpa.project.World.WFCNode addLeft(com.cpa.project.World.WFCNode node){
//        Left.addNode(node);
//        node.Right.addNode(this);
//        return this;
//    }
//
//    public com.cpa.project.World.WFCNode addRight(com.cpa.project.World.WFCNode node){
//        Right.addNode(node);
//        node.Left.addNode(this);
//        return this;
//    }
//
//    public void setEntropy(double entropy){
//        this.entropy = entropy;
//    }
//
//    public double getEntropy(){
//        return this.entropy;
//    }
//
//    public ArrayList<com.cpa.project.World.WFCNode> getCompatibleNodes(int i) {
//        switch (i) {
//            case 0:
//                return Top.compatibleNodes;
//            case 1:
//                return Bottom.compatibleNodes;
//            case 2:
//                return Left.compatibleNodes;
//            case 3:
//                return Right.compatibleNodes;
//            default:
//                return new ArrayList<>();
//        }
//    }
////
////    public WFCNode addTopRight(WFCNode node){
////        TopRight.addNode(node);
////        node.BottomLeft.addNode(this);
////        return this;
////    }
////
////    public WFCNode addTopLeft(WFCNode node){
////        TopLeft.addNode(node);
////        node.BottomRight.addNode(this);
////        return this;
////    }
////
////    public WFCNode addBottomRight(WFCNode node){
////        BottomRight.addNode(node);
////        node.TopLeft.addNode(this);
////        return this;
////    }
////
////    public WFCNode addBottomLeft(WFCNode node){
////        BottomLeft.addNode(node);
////        node.TopRight.addNode(this);
////        return this;
////    }
//
//
//
//
//}
//
//class WFCConnection {
//    ArrayList<com.cpa.project.World.WFCNode> compatibleNodes ;
//    public WFCConnection() {
//        compatibleNodes = new ArrayList<>();
//    }
//
//    public void addNode(com.cpa.project.World.WFCNode node){
//        if (!compatibleNodes.contains(node))
//            compatibleNodes.add(node);
//    }
//
//    public void removeNode(com.cpa.project.World.WFCNode node){
//        compatibleNodes.remove(node);
//    }
//
//    // print the compatible nodes
//    public void printCompatibleNodes(){
//        for (int i = 0; i < compatibleNodes.size(); i++) {
//            System.err.println(compatibleNodes.get(i).name);
//        }
//    }
//}
//
///**
// *  classe qui permet de gérer la map et les tiles de la map
// *  Algorithme de Wave Function Collapse : https://robertheaton.com/2018/12/17/wavefunction-collapse-algorithm/
// * this is the Simple Tiled Implementation of the Wave Function Collapse Algorithm
// */
//public class WFC {
//    // the Size of the map : width and height
//    private int width,Height;
//    // a 2D array of WFCNode that will store our collapsed tiles so we can reference them later
//    private com.cpa.project.World.WFCNode[][] map;
//
//    // the list of all the nodes that we have
//    public ArrayList<com.cpa.project.World.WFCNode> nodes = new ArrayList<>();
//
//    // the list of all the tiles that we will output
//    ArrayList<Tile> outputTiles = new ArrayList<>();
//
//    // a list of the vector2 that we will use to collapse the tiles
//    private ArrayList<Vector2> toCollapse = new ArrayList<>();
//
//
//    // an array of the offsets that we will use to get the neighbors of a tile
//    private Vector2[] offsets = new Vector2[]{
//            new Vector2(0,1), // top
//            new Vector2(0,-1), // bottom
//            new Vector2(1,0), // right
//            new Vector2(-1,0) // left
////            new Vector2(1,1), // top right
////            new Vector2(-1,1), // top left
////            new Vector2(1,-1), // bottom right
////            new Vector2(-1,-1) // bottom left
//    };
//
//    com.cpa.project.Tiles.terrainFloorTiles terrainFloorTiles;
//
//    Tile[][] sandCenterWGrass;
//    Tile[][] grassCenterWSand;
//    Tile[][] rockCenterWSand;
//    Tile[][] voidCenterWRock;
//    Tile[][] waterTiles;
//
//
//    com.cpa.project.World.WFCNode centerStart ;
//
//    // the constructor of the WFC
//    public WFC(int width, int height) {
//        this.width = width;
//        this.Height = height;
//        this.map = new com.cpa.project.World.WFCNode[width][height];
//        this.terrainFloorTiles = new terrainFloorTiles();
//        this.sandCenterWGrass = terrainFloorTiles.getTileBox(0);
//        this.grassCenterWSand = terrainFloorTiles.getTileBox(10);
//        this.rockCenterWSand = terrainFloorTiles.getTileBox(20);
//        this.voidCenterWRock = terrainFloorTiles.getTileBox(30);
//        this.waterTiles = terrainFloorTiles.getTileBox(40);
//
//        initNodes();
//        collapseWorld();
//
//    }
//
//    // this function will initialize the nodes that we have in the world
//    // and the connections between them
//    private void initNodes() {
//        // we will start with sandCenterWGrass
//        Map<Integer, com.cpa.project.World.WFCNode> allNodes = new HashMap<>();
//        allNodes.put(0, new com.cpa.project.World.WFCNode("sandCenterWGrass[0][0]", sandCenterWGrass[0][0] , 0.1));
//        allNodes.put(1, new com.cpa.project.World.WFCNode("sandCenterWGrass[0][1]", sandCenterWGrass[0][1] , 0.1));
//        allNodes.put(2, new com.cpa.project.World.WFCNode("sandCenterWGrass[0][2]", sandCenterWGrass[0][2] , 0.1));
//        allNodes.put(3, new com.cpa.project.World.WFCNode("sandCenterWGrass[1][0]", sandCenterWGrass[1][0] , 0.1));
//        allNodes.put(4, new com.cpa.project.World.WFCNode("sandCenterWGrass[1][1]", sandCenterWGrass[1][1] , 0.9));
//        allNodes.put(5, new com.cpa.project.World.WFCNode("sandCenterWGrass[1][2]", sandCenterWGrass[1][2] , 0.1));
//        allNodes.put(6, new com.cpa.project.World.WFCNode("sandCenterWGrass[2][0]", sandCenterWGrass[2][0] , 0.1));
//        allNodes.put(7, new com.cpa.project.World.WFCNode("sandCenterWGrass[2][1]", sandCenterWGrass[2][1] , 0.1));
//        allNodes.put(8, new com.cpa.project.World.WFCNode("sandCenterWGrass[2][2]", sandCenterWGrass[2][2] , 0.1));
//
//
//        allNodes.put(10, new com.cpa.project.World.WFCNode("grassCenterWSand[0][0]", grassCenterWSand[0][0] , 0.1));
//        allNodes.put(11, new com.cpa.project.World.WFCNode("grassCenterWSand[0][1]", grassCenterWSand[0][1] , 0.1));
//        allNodes.put(12, new com.cpa.project.World.WFCNode("grassCenterWSand[0][2]", grassCenterWSand[0][2] , 0.1));
//        allNodes.put(13, new com.cpa.project.World.WFCNode("grassCenterWSand[1][0]", grassCenterWSand[1][0] , 0.1));
//        allNodes.put(14, new com.cpa.project.World.WFCNode("grassCenterWSand[1][1]", grassCenterWSand[1][1] , 0.9));
//        allNodes.put(15, new com.cpa.project.World.WFCNode("grassCenterWSand[1][2]", grassCenterWSand[1][2] , 0.1));
//        allNodes.put(16, new com.cpa.project.World.WFCNode("grassCenterWSand[2][0]", grassCenterWSand[2][0] , 0.1));
//        allNodes.put(17, new com.cpa.project.World.WFCNode("grassCenterWSand[2][1]", grassCenterWSand[2][1] , 0.1));
//        allNodes.put(18, new com.cpa.project.World.WFCNode("grassCenterWSand[2][2]", grassCenterWSand[2][2] , 0.1));
//
//
//
//
//
//
//        // we add the nodes to the nodes list
//        nodes.addAll(allNodes.values());
//
//        centerStart = allNodes.get(4);
//
//
//        setConnections(allNodes, 0);
//        setConnections(allNodes,  10);
//
////         we set the outer connections
//        setOuterConnections(allNodes,  0, 10);
//        setOuterConnections(allNodes , 10, 0);
//
//
//        // check compatibilitis of 0
//        System.err.println("Top" ) ;
//        allNodes.get(7).Top.printCompatibleNodes();
//        System.err.println("Bottom" ) ;
//        allNodes.get(7).Bottom.printCompatibleNodes();
//        System.err.println("Left" ) ;
//        allNodes.get(7).Left.printCompatibleNodes();
//        System.err.println("Right" ) ;
//        allNodes.get(7).Right.printCompatibleNodes();
//
//    }
//
//
//
//    void setConnections(Map<Integer, com.cpa.project.World.WFCNode> allNodes, int baseIndex) {
//        allNodes.get(baseIndex+4).addRight(allNodes.get(baseIndex+4))
//                .addBottom(allNodes.get(baseIndex+4)).addLeft(allNodes.get(baseIndex+4))
//                .addTop(allNodes.get(baseIndex+4));
//
//        allNodes.get(baseIndex).addRight(allNodes.get(baseIndex + 1)).
//                addBottom(allNodes.get(baseIndex + 3));
//        allNodes.get(baseIndex + 1).addLeft(allNodes.get(baseIndex)).
//                addRight(allNodes.get(baseIndex + 2)).
//                addBottom(allNodes.get(baseIndex + 4)).addLeft(allNodes.get(baseIndex + 1)).addRight(allNodes.get(baseIndex + 1));
//        allNodes.get(baseIndex + 2).addLeft(allNodes.get(baseIndex + 1)).
//                addBottom(allNodes.get(baseIndex + 5));
//
//        allNodes.get(baseIndex + 3).addTop(allNodes.get(baseIndex)).
//                addRight(allNodes.get(baseIndex + 4)).
//                addBottom(allNodes.get(baseIndex + 6)).addTop(allNodes.get(baseIndex + 3)).addBottom(allNodes.get(baseIndex + 3));
//        allNodes.get(baseIndex + 4).addTop(allNodes.get(baseIndex + 1))
//                .addLeft(allNodes.get(baseIndex + 3))
//                .addRight(allNodes.get(baseIndex + 5))
//                .addBottom(allNodes.get(baseIndex + 7));
//        allNodes.get(baseIndex + 5).addTop(allNodes.get(baseIndex + 2))
//                .addLeft(allNodes.get(baseIndex + 4))
//                .addBottom(allNodes.get(baseIndex + 8))
//                .addTop(allNodes.get(baseIndex + 5)).
//                addBottom(allNodes.get(baseIndex + 5));
//
//        allNodes.get(baseIndex + 6).addTop(allNodes.get(baseIndex + 3))
//                .addRight(allNodes.get(baseIndex + 7));
//        allNodes.get(baseIndex + 7).addTop(allNodes.get(baseIndex + 4))
//                .addLeft(allNodes.get(baseIndex + 6))
//                .addRight(allNodes.get(baseIndex + 8)).addRight(allNodes.get(baseIndex + 6)).addLeft(allNodes.get(baseIndex + 6));
//        allNodes.get(baseIndex + 8).addTop(allNodes.get(baseIndex + 5))
//                .addLeft(allNodes.get(baseIndex + 7));
//
//    }
//
//
//    // i'm adding the outer connection from one box tile to another
//    // example : sandCenterWGrass to grassCenterWSand
//    // sandCenterWGrass[0][0] to grassCenterWSand[1][1] in the top direction and left direction
//    // sandCenterWGrass[0][2] to grassCenterWSand[1][1] in the top direction and right direction
//    // sandCenterWGrass[1][0] to grassCenterWSand[1][1] in the left direction
//    // sandCenterWGrass[1][2] to grassCenterWSand[1][1] in the right direction
//    // sandCenterWGrass[2][0] to grassCenterWSand[1][1] in the bottom direction and left direction
//    // sandCenterWGrass[2][2] to grassCenterWSand[1][1] in the bottom direction and right direction
//    public void setOuterConnections(Map<Integer, com.cpa.project.World.WFCNode> allNodes, int baseIndex1, int baseIndex2) {
//        // top left
//        allNodes.get(baseIndex1).addLeft(allNodes.get(baseIndex2+4)).addTop(allNodes.get(baseIndex2+4));
//
//        // top right
//        allNodes.get(baseIndex1+2).addRight(allNodes.get(baseIndex2+4)).addTop(allNodes.get(baseIndex2+4));
//
//        // top
//        allNodes.get(baseIndex1+1).addTop(allNodes.get(baseIndex2+4));
//
//        // bottom left
//        allNodes.get(baseIndex1+6).addLeft(allNodes.get(baseIndex2+4)).addBottom(allNodes.get(baseIndex2+4));
//
//        // bottom right
//        allNodes.get(baseIndex1+8).addRight(allNodes.get(baseIndex2+4)).addBottom(allNodes.get(baseIndex2+4));
//
//        // bottom
//        allNodes.get(baseIndex1+7).addBottom(allNodes.get(baseIndex2+4));
//
//        // left
//        allNodes.get(baseIndex1+3).addLeft(allNodes.get(baseIndex2+4));
//
//        // right
//        allNodes.get(baseIndex1+5).addRight(allNodes.get(baseIndex2+4));
//
//
//    }
//
//
//    // this function will collapse the world
////    private void collapseWorld() {
////        toCollapse.clear();
////        toCollapse.add(new Vector2(0, 0)); // Starting from the center
////
////        boolean first = false;
////        while (!toCollapse.isEmpty()) {
////            int x = (int) toCollapse.get(0).x;
////            int y = (int) toCollapse.get(0).y;
////
////            ArrayList<WFCNode> potentialNodes = new ArrayList<>(nodes);
////
////            for (int i = 0; i < offsets.length; i++) {
////                Vector2 neighbor = new Vector2(x + offsets[i].x, y + offsets[i].y);
////                if (IsInGrid(neighbor)) {
////                    WFCNode neighborNode = map[(int) neighbor.x][(int) neighbor.y];
////                    if (neighborNode != null) {
////                        switch (i) {
////                            case 0:
////                                whittleNodes(potentialNodes, neighborNode.Bottom.compatibleNodes );
////                                break;
////                            case 1:
////                                whittleNodes(potentialNodes, neighborNode.Top.compatibleNodes );
////                                break;
////                            case 2:
////                                whittleNodes(potentialNodes, neighborNode.Left.compatibleNodes );
////                                break;
////                            case 3:
////                                whittleNodes(potentialNodes, neighborNode.Right.compatibleNodes);
////                                break;
////                        }
////                    } else {
////                        if (!toCollapse.contains(neighbor)) {
////                            toCollapse.add(neighbor);
////                        }
////                    }
////                }
////            }
////
////            // Now we have whittled down the potential nodes based on neighbors
////            if (potentialNodes.isEmpty()) {
////                // If no compatible nodes, select a random one
////                map[x][y] = nodes.get(0);
////            } else {
////                if (!first) {
////                    map[x][y] = centerStart;
////                    first = true;
////                } else {
////                    map[x][y] = getRandomNode(potentialNodes);
////                }
////            }
////
////            // Add the tile to the output tiles
////            Tile tile = map[x][y].tile.clone((int) Math.round(map[x][y].tile.getId() + x + y * Math.random()));
////            tile.setIsReachable(!Objects.equals(map[x][y].name, "sandCenterWGrass[2][1]")); // Adjust reachability condition
////            tile.setPosition(new Vector2(x * width, y * Height));
////            outputTiles.add(tile);
////
////            // Remove the tile from the toCollapse list
////            toCollapse.remove(0);
////        }
////    }
//
////
//    private void collapseWorld() {
//        toCollapse.clear();
//        toCollapse.add(new Vector2(width / 2, Height / 2)); // Starting from the center
//
//        while (!toCollapse.isEmpty()) {
//
//            int x = (int) toCollapse.get(0).x;
//            int y = (int) toCollapse.get(0).y;
//
//            ArrayList<WFCNode> potentialNodes = new ArrayList<>(nodes);
//
//            for (int i = 0; i < offsets.length; i++) {
//                Vector2 neighbor = new Vector2(x + offsets[i].x, y + offsets[i].y);
//                if (IsInGrid(neighbor)) {
//                    WFCNode neighborNode = map[(int) neighbor.x][(int) neighbor.y];
//                    if (neighborNode != null) {
//                        switch (i) {
//                            case 0:
//                                whittleNodes(potentialNodes, neighborNode.Bottom.compatibleNodes);
//                                break;
//                            case 1:
//                                whittleNodes(potentialNodes, neighborNode.Top.compatibleNodes);
//                                break;
//                            case 2:
//                                whittleNodes(potentialNodes, neighborNode.Left.compatibleNodes);
//                                break;
//                            case 3:
//                                whittleNodes(potentialNodes, neighborNode.Right.compatibleNodes);
//                                break;
//                        }
//                    } else {
//                        if (!toCollapse.contains(neighbor)) {
//                            toCollapse.add(neighbor);
//                        }
//                    }
//                }
//            }
//
//            // Now we have whittled down the potential nodes based on neighbors
//            if (!potentialNodes.isEmpty()) {
//                // If there are compatible nodes, select a random one
//                WFCNode selectedNode = getRandomNode(potentialNodes);
//                map[x][y] = selectedNode;
//
//                // Check if the current configuration is valid
//                if (isValidConfiguration(x, y)) {
//                    // Add the tile to the output tiles
//                    Tile tile = map[x][y].tile.clone((int) Math.round(map[x][y].tile.getId() + x + y * Math.random()));
//                    tile.setIsReachable(!Objects.equals(map[x][y].name, "sandCenterWGrass[2][1]")); // Adjust reachability condition
//                    tile.setPosition(new Vector2(x * width, y * Height));
//                    outputTiles.add(tile);
//
//                    // Remove the tile from the toCollapse list
//                    toCollapse.remove(0);
//                } else {
//                    // If the current configuration is not valid, backtrack
//                    map[x][y] = null;
//
//                    // add the tile to the end of the toCollapse list
//                    toCollapse.add(new Vector2(x, y));
//                    Collections.shuffle(toCollapse);
//                }
//            } else {
//                // If no compatible nodes found, backtrack
//                toCollapse.remove(0);
//            }
//        }
// }
//
//    private void collapseWorld() {
//        // Use a queue for BFS
//        Queue<Vector2> queue = new LinkedList<>(toCollapse);
//
//        queue.add(new Vector2(width / 2, Height / 2)); // Starting from the center
//
//        while (!queue.isEmpty()) {
//            Vector2 currentPos = queue.poll();
//            int x = (int) currentPos.x;
//            int y = (int) currentPos.y;
//
//            ArrayList<com.cpa.project.World.WFCNode> potentialNodes = new ArrayList<>(nodes);
//
//            // Iterate through neighbors and update potentialNodes
//            for (int i = 0; i < offsets.length; i++) {
//                Vector2 neighbor = new Vector2(x + offsets[i].x, y + offsets[i].y);
//                if (IsInGrid(neighbor)) {
//                    com.cpa.project.World.WFCNode neighborNode = map[(int) neighbor.x][(int) neighbor.y];
//                    if (neighborNode != null) {
//                        switch (i) {
//                            case 0:
//                                whittleNodes(potentialNodes, neighborNode.Bottom.compatibleNodes);
//                                break;
//                            case 1:
//                                whittleNodes(potentialNodes, neighborNode.Top.compatibleNodes);
//                                break;
//                            case 2:
//                                whittleNodes(potentialNodes, neighborNode.Left.compatibleNodes);
//                                break;
//                            case 3:
//                                whittleNodes(potentialNodes, neighborNode.Right.compatibleNodes);
//                                break;
//                        }
//                    } else {
//                        if (!queue.contains(neighbor)) {
//                            queue.add(neighbor);
//                        }
//                    }
//                }
//
//            }
//
//            // Now we have whittled down the potential nodes based on neighbors
//            if (!potentialNodes.isEmpty()) {
//                // If there are compatible nodes, select a random one
//                com.cpa.project.World.WFCNode selectedNode = getRandomNode(potentialNodes);
//                map[x][y] = selectedNode;
//
//                // Check if the current configuration is valid
//                if (isValidConfiguration(x, y)) {
//                    // Add the tile to the output tiles
//                    Tile tile = map[x][y].tile.clone((int) Math.round(map[x][y].tile.getId() + x + y * Math.random()));
//                    tile.setIsReachable(!Objects.equals(map[x][y].name, "sandCenterWGrass[2][1]")); // Adjust reachability condition
//                    tile.setPosition(new Vector2(x * width, y * Height));
//                    outputTiles.add(tile);
//                } else {
//                    // If the current configuration is not valid, backtrack
//                    map[x][y] = null;
//                }
//            }
//            else{
//
//                // this means that there are no compatible nodes
//                // so we need to remove the current node from the queue
//                // and also remove the neighbors from the queue
//                // and redo the whole zone
//
//            }
//        }
//
//
//    }
//
//
////    private void collapseWorld() {
////        // Use a queue for BFS
////        Queue<Vector2> queue = new LinkedList<>(toCollapse);
////
////        queue.add(new Vector2(width / 2, Height / 2)); // Starting from the center
////
////
////        while (!queue.isEmpty()) {
////            Vector2 currentPos = queue.poll();
////            int x = (int) currentPos.x;
////            int y = (int) currentPos.y;
////
////            ArrayList<WFCNode> potentialNodes = new ArrayList<>(nodes);
////
////            // Iterate through neighbors and update potentialNodes
////            for (int i = 0; i < offsets.length; i++) {
////                Vector2 neighbor = new Vector2(x + offsets[i].x, y + offsets[i].y);
////                if (IsInGrid(neighbor)) {
////                    WFCNode neighborNode = map[(int) neighbor.x][(int) neighbor.y];
////                    if (neighborNode != null) {
////                        switch (i) {
////                            case 0:
////                                whittleNodes(potentialNodes, neighborNode.Bottom.compatibleNodes);
////                                break;
////                            case 1:
////                                whittleNodes(potentialNodes, neighborNode.Top.compatibleNodes);
////                                break;
////                            case 2:
////                                whittleNodes(potentialNodes, neighborNode.Left.compatibleNodes);
////                                break;
////                            case 3:
////                                whittleNodes(potentialNodes, neighborNode.Right.compatibleNodes);
////                                break;
////                        }
////                    } else {
////                        if (!queue.contains(neighbor)) {
////                            queue.add(neighbor);
////                        }
////                    }
////                }
////
////            }
////
////            // Now we have whittled down the potential nodes based on neighbors
////            if (!potentialNodes.isEmpty()) {
////                // If there are compatible nodes, select a random one
////                WFCNode selectedNode = getRandomNode(potentialNodes);
////                map[x][y] = selectedNode;
////
////                // Check if the current configuration is valid
////                if (isValidConfiguration(x, y)) {
////                    // Add the tile to the output tiles
////                    Tile tile = map[x][y].tile.clone((int) Math.round(map[x][y].tile.getId() + x + y * Math.random()));
////                    tile.setIsReachable(!Objects.equals(map[x][y].name, "sandCenterWGrass[2][1]")); // Adjust reachability condition
////                    tile.setPosition(new Vector2(x * width, y * Height));
////                    outputTiles.add(tile);
////                } else {
////                    // If the current configuration is not valid, backtrack
////                    map[x][y] = null;
////
////                    // Remove the current tile and its neighbors from the queue
////                    queue.remove(currentPos);
////                    for (Vector2 offset : offsets) {
////                        Vector2 neighbor = new Vector2(x + offset.x, y + offset.y);
////                        if (IsInGrid(neighbor)) {
////                            queue.remove(neighbor);
////                        }
////                    }
////
////                    // Retry collapsing the affected zone
////                    queue.add(currentPos);
////                }
////
////
////            } else {
////                // No compatible nodes found, handle as needed
////
////            }
////        }
////
////    }
////
//
//    // Method to get potential nodes based on the neighbors and their neighbors
//    private ArrayList<com.cpa.project.World.WFCNode> getPotentialNodes(int x, int y) {
//        ArrayList<com.cpa.project.World.WFCNode> potentialNodes = new ArrayList<>(nodes);
//
//        // Iterate through neighbors and update potentialNodes
//        for (int i = 0; i < offsets.length; i++) {
//            Vector2 neighbor = new Vector2(x + offsets[i].x, y + offsets[i].y);
//            if (IsInGrid(neighbor)) {
//                com.cpa.project.World.WFCNode neighborNode = map[(int) neighbor.x][(int) neighbor.y];
//                if (neighborNode != null) {
//                    switch (i) {
//                        case 0:
//                            whittleNodes(potentialNodes, neighborNode.Bottom.compatibleNodes);
//                            break;
//                        case 1:
//                            whittleNodes(potentialNodes, neighborNode.Top.compatibleNodes);
//                            break;
//                        case 2:
//                            whittleNodes(potentialNodes, neighborNode.Left.compatibleNodes);
//                            break;
//                        case 3:
//                            whittleNodes(potentialNodes, neighborNode.Right.compatibleNodes);
//                            break;
//                    }
//                }
//            }
//        }
//
//        return potentialNodes;
//    }
//
//
//
//
//    // Check if the current configuration is valid
////    private boolean isValidConfiguration(int x, int y) {
////        if (x > 0 && map[x - 1][y] != null && !map[x][y].Left.compatibleNodes.contains(map[x - 1][y])) {
////            return false;
////        }
////        if (x < width - 1 && map[x + 1][y] != null && !map[x][y].Right.compatibleNodes.contains(map[x + 1][y])) {
////            return false;
////        }
////        if (y > 0 && map[x][y - 1] != null && !map[x][y].Bottom.compatibleNodes.contains(map[x][y - 1])) {
////            return false;
////        }
////        if (y < Height - 1 && map[x][y + 1] != null && !map[x][y].Top.compatibleNodes.contains(map[x][y + 1])) {
////            return false;
////        }
////        return true;
////    }
//
//    private boolean isValidConfiguration(int x, int y) {
//        com.cpa.project.World.WFCNode currentNode = map[x][y];
//        for (int i = 0; i < offsets.length; i++) {
//            Vector2 neighbor = new Vector2(x + offsets[i].x, y + offsets[i].y);
//            if (IsInGrid(neighbor)) {
//                com.cpa.project.World.WFCNode neighborNode = map[(int) neighbor.x][(int) neighbor.y];
//                if (neighborNode != null) {
//                    switch (i) {
//                        case 0:
//                            if (!currentNode.Top.compatibleNodes.contains(neighborNode)) {
//                                return false;
//                            }
//                            break;
//                        case 1:
//                            if (!currentNode.Bottom.compatibleNodes.contains(neighborNode)) {
//                                return false;
//                            }
//                            break;
//                        case 2:
//                            if (!currentNode.Right.compatibleNodes.contains(neighborNode)) {
//                                return false;
//                            }
//                            break;
//                        case 3:
//                            if (!currentNode.Left.compatibleNodes.contains(neighborNode)) {
//                                return false;
//                            }
//                            break;
//                    }
//                }
//            }
//        }
//        return true;
//    }
//    // Method to get a random node from a list of nodes
//    private com.cpa.project.World.WFCNode getRandomNode(ArrayList<com.cpa.project.World.WFCNode> nodes) {
//        // take into account the entropy of the nodes
//        double totalEntropy = nodes.stream().mapToDouble(node -> node.entropy).sum();
//        double randomValue = Math.random() * totalEntropy;
//
//        for (com.cpa.project.World.WFCNode node : nodes) {
//            randomValue -= node.entropy;
//            if (randomValue <= 0) {
//                return node;
//            }
//        }
//
//        return nodes.get((int) (Math.random() * nodes.size()));
//    }
//
//    // Method to check if coordinates are within the grid bounds
//    private boolean IsInGrid(Vector2 coords) {
//        return coords.x >= 0 && coords.x < width && coords.y >= 0 && coords.y < Height;
//    }
//
//
//
//    // get the output tiles
//    public ArrayList<Tile> getOutputTiles() {
//        return outputTiles;
//    }
//
//
//    // compares a list of potential nodes to a lmist of a valid nodes and remove
//    // all non valid nodes from the potential nodes
//    private void whittleNodes(ArrayList<com.cpa.project.World.WFCNode> potentialNodes, ArrayList<com.cpa.project.World.WFCNode> validNodes ) {
//        for (int i = 0; i < potentialNodes.size(); i++) {
//            if (!validNodes.contains(potentialNodes.get(i))) {
//                potentialNodes.remove(i);
//                i--;
//            }
//        }
//    }
//
//
//}