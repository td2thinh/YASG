package com.cpa.project.Utils.Pathfinding;

import com.badlogic.gdx.math.Vector2;
import com.cpa.project.State.PlayState;
import com.cpa.project.Tiles.Tile;

import java.util.*;

public class GradientGraph {

    private Location[][] graph;
    private final Tile[][] tiles = PlayState.map.getTiles();


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
        Queue<Vector2> frontier = new LinkedList<>();
        // HashMap to keep track of the cost to reach a tile
        Map<Vector2, Integer> costSoFar = new HashMap<>();
        Location start = new Location(tileX, tileY, 0);
        Vector2 startTile = new Vector2(tileX, tileY);
        frontier.add(startTile);
        costSoFar.put(startTile, 0);

        Set<Vector2> seen = new HashSet<>();
        seen.add(startTile);

        this.graph[tileX][tileY] = start;

        Vector2[] directions = {new Vector2(0, 1),
                new Vector2(0, -1),
                new Vector2(1, 0),
                new Vector2(-1, 0)};

        while (!frontier.isEmpty() && numTiles < 5000) {
            Vector2 current = frontier.poll();
            for (Vector2 next : getNeighbors(current)) {
                int nextX = (int) next.x;
                int nextY = (int) next.y;
                if (!seen.contains(next)) {
                    if (tiles[nextX][nextY].isReachable()) {
                        frontier.add(next);
                        int newCost = costSoFar.get(current) + 1;
                        costSoFar.put(next, newCost);
                        for (Vector2 dir : directions) {
                            if (current.x - dir.x == next.x && current.y - dir.y == next.y) {
                                graph[nextX][nextY] = new Location(nextX, nextY, newCost, dir);;
                            }
                        }
                    }
                    seen.add(next);
                    numTiles++;
                }
            }
        }
    }

    public Vector2 getDirection(int x, int y) {
        return graph[x][y].getDirection();
    }

    private List<Vector2> getNeighbors(Vector2 tile) {
        List<Vector2> neighbors = new ArrayList<>();
        int x = (int) tile.x;
        int y = (int) tile.y;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < PlayState.map.getWidth() && newY >= 0 && newY < PlayState.map.getHeight()) {
                neighbors.add(new Vector2(newX, newY));
            }
        }
        return neighbors;
    }
}
