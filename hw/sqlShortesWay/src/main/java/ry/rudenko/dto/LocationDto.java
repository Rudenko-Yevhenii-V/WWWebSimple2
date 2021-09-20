package ry.rudenko.dto;

import ry.rudenko.entity.Location;

public class LocationDto
 {
   String name;
  public LocationDto(Location location) {
    this.name = location.name();
  }
}