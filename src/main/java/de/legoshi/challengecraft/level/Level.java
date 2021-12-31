package de.legoshi.challengecraft.level;

import de.legoshi.challengecraft.utils.FW;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Level {

    private String name;

    private double x1;
    private double y1;
    private double z1;

    private double x2;
    private double y2;
    private double z2;

    private double spawnX;
    private double spawnY;
    private double spawnZ;

    public Level(FW fw, String name) {
        this.name = fw.getString(name);
        this.x1 = Double.parseDouble(fw.getString(name+".x1"));
        this.y1 = Double.parseDouble(fw.getString(name+".y1"));
        this.z1 = Double.parseDouble(fw.getString(name+".z1"));

        this.x2 = Double.parseDouble(fw.getString(name+".x2"));
        this.y2 = Double.parseDouble(fw.getString(name+".y2"));
        this.z2 = Double.parseDouble(fw.getString(name+".z2"));

        this.spawnX = Double.parseDouble(fw.getString(name+".spawnX"));
        this.spawnY = Double.parseDouble(fw.getString(name+".spawnY"));
        this.spawnZ = Double.parseDouble(fw.getString(name+".spawnZ"));
    }

}
