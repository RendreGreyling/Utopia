package controller.listeners;

import controller.util.GameMode;
import controller.util.Property;
import model.GameModel;
import model.common.Coordinate;
import model.common.Satisfaction;
import model.exceptions.OperationException;
import model.util.Level;
import model.zone.*;

public class ZoneBuildingListener extends ServiceListener {


    public ZoneBuildingListener(Property property) {
        super(property);
    }

    @Override
    public void update(Coordinate coordinate) {

        GameMode gmo = property.getGameMode();
        GameModel gm = property.getGameModel();
        System.out.println(gmo);
        Zone zone = null;
        switch (gmo) {
            // TODO replace by factory
            case COMMERCIAL_MODE -> zone = new CommercialZone(Level.CONSTRUCTING, 0, new ZoneStatistics(0,0,new Satisfaction()), gm.getCurrentDate(), coordinate);
            case INDUSTRIAL_MODE -> zone = new IndustrialZone(Level.CONSTRUCTING, 0, new ZoneStatistics(0,0,new Satisfaction()), gm.getCurrentDate(), coordinate);
            case RESIDENTIAL_MODE -> zone = new ResidentialZoneFactory(gm).createZone(coordinate);
        }
        System.out.println("Created" + zone);
        try {
            // add created zone to game model.
            gm.addZone(zone);
            // call back
            property.getCallBack().updateGridSystem(zone.getCoordinate(), zone);
        } catch (OperationException e) {
            System.out.println("ha ha ha ha");
        }
    }
}