public void unloadVessel(Vessel vessel) {
    for (Cargo cargo : vessel.getCargos()) {
        if (cargo.isDangerous()) {
            ship.getDangerousCargo().add(cargo);
        } else {
            ship.getRegularCargo().add(cargo);
        }
    }
    vessel.getCargos().clear();