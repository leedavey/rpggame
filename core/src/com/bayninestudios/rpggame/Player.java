package com.bayninestudios.rpggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player {

    final int HITFRAME = 0;
    final public int MOVESPEED = 100;
    float posX, posY;
    TiledMapTileLayer collisionLayer;
    float moveX, moveY;

    public Player(TiledMapTileLayer collLayer) {
        collisionLayer = collLayer;
        moveX = 0f;
        moveY = 0f;
        posX = 704; // 44*16
        posY = 1152; // (99-27)*16
    }

    public void movePlayer(float delta) {
        float newX = 0;
        float newY = 0;
        if (moveX > 0) {
            newX = 16-HITFRAME+posX + moveX * delta;
        }
        if (moveX < 0) {
            newX = (posX + moveX * delta)+HITFRAME;
        }
        if (moveY > 0) {
            newY = 16-HITFRAME+posY + moveY * delta;
        }
        if (moveY < 0) {
            newY = (posY + moveY * delta)+HITFRAME;
        }
        int cellY = (int)(posY/16);
        int cellX = (int)(posX/16);

        TiledMapTileLayer.Cell cell;
        if (moveX != 0) {
            cell = collisionLayer.getCell((int) (newX / 16), cellY);
            if (!cell.getTile().getProperties().containsKey("notpassable")) {
                posX += moveX * delta;
            } else {
                Gdx.app.log("move", "Blocked on X, "+cell.getTile().getId());
            }
        }

        if (moveY != 0) {
            cell = collisionLayer.getCell(cellX, (int) ((newY) / 16));
            if (!cell.getTile().getProperties().containsKey("notpassable")) {
                posY += moveY * delta;
            } else {
                Gdx.app.log("move", "Blocked on Y, "+cell.getTile().getId());
            }
        }
        Gdx.app.log("move", "pos, "+(int)(posX/16)+","+(99-(int)(posY/16))+","+MOVESPEED*delta);
    }
}
