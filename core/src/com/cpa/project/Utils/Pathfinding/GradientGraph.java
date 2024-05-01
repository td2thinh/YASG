package com.cpa.project.Utils.Pathfinding;

import com.badlogic.gdx.math.Vector2;
import com.cpa.project.State.PlayState;
import com.cpa.project.Tiles.Tile;

import java.util.*;

public class GradientGraph {

    private Location[][] graph;
    private final Tile[][] tiles = PlayState.map.getTiles();

    private Set<Location> seen;
    private Queue<Location> frontier;
    private Map<Location, Integer> costSoFar;


    private final int[][] directionsInt = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public GradientGraph() {
        this.graph = new Location[PlayState.map.getWidth()][PlayState.map.getHeight()];
    }

    // Source: https://www.redblobgames.com/pathfinding/a-star/implementation.html
    public void compute() {
        int numTiles = 0;
        Vector2 playerTileXY = PlayState.map.getEntityTileXY(PlayState.player);
        int tileX = (int) playerTileXY.x;
        int tileY = (int) playerTileXY.y;

        // Frontier queue holds the tiles that we need to explore
        frontier = new LinkedList<>();
        // HashMap to keep track of the cost to reach a tile
        costSoFar = new HashMap<>();
        Location start = new Location(tileX, tileY, 0);

        seen = new HashSet<>();
        frontier.add(start);
        costSoFar.put(start, 0);


        seen.add(start);

        this.graph[tileX][tileY] = start;


        while (!frontier.isEmpty() && numTiles < 6000) {
            Location current = frontier.poll();
            for (Location next : getNeighbors(current)) {

                int nextX = next.getX();
                int nextY = next.getY();
                if (!seen.contains(next)) {
                    if (tiles[nextX][nextY].isReachable()) {
                        frontier.add(next);
                        int newCost = costSoFar.get(current) + 1;
                        costSoFar.put(next, newCost);
                        next.setCost(newCost);
                        graph[nextX][nextY] = next;
                    } else {
                        graph[nextX][nextY] = new Location(nextX, nextY, 0, new Vector2(0, 0));
                    }
                    seen.add(next);
                    numTiles++;
                }
            }
        }

        seen = null;
        frontier = null;
        costSoFar = null;

    }

    public Vector2 getDirection(int x, int y) {
        return (x <= graph.length && y <= graph[0].length && graph[x][y] != null) ? graph[x][y].getDirection() : new Vector2(0, 0);
    }

    private List<Location> getNeighbors(Location tile) {
        List<Location> neighbors = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for (int[] dir : directionsInt) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < PlayState.map.getWidth() && newY >= 0 && newY < PlayState.map.getHeight()) {
                Vector2 direction = new Vector2(-dir[0],-dir[1]);
                Location newTile = new Location(newX, newY, 0, direction.nor());
                neighbors.add(newTile);
            }
        }
        return neighbors;
    }
}