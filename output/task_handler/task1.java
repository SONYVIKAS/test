public void unloadVessel(Ship ship, Port port) {
    for (Cargo cargo : ship.getCargos()) {
        port.addCargo(cargo);
    }
    ship.clearCargos();