let map, infoWindow;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: -34.397, lng: 150.644 },
    zoom: 15
  });
  infoWindow = new google.maps.InfoWindow();

  // Try HTML5 geolocation.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        const pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
        console.log(pos);
        infoWindow.setPosition(pos);
        infoWindow.setContent("You are here");
        infoWindow.open(map);
        map.setCenter(pos);
      },
      () => {
        handleLocationError(true, infoWindow, map.getCenter());
      }
    );
  } else {
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());
  }

  const image = {
    url:
      "https://i7.pngguru.com/preview/193/660/856/computer-icons-woman-avatar-clip-art-avatar-girl.jpg",
    // This marker is 32 pixels wide by 32 pixels high.
    scaledSize: new google.maps.Size(32, 32),
    // The origin for this image is (0, 0).
    origin: new google.maps.Point(0, 0),
    // The anchor for this image is the base of the flagpole at (0, 32).
    anchor: new google.maps.Point(0, 32)
  };
  var features = [
    {
      position: new google.maps.LatLng(-33.781721, 151.1863),
      type: "info"
    },
    {
      position: new google.maps.LatLng(-33.791539, 151.1782),
      type: "info"
    },
    {
      position: new google.maps.LatLng(-33.79863, 151.1781),
      type: "info"
    },
   	{
      position: new google.maps.LatLng(-33.79762, 151.17083),
      type: "info"
    },
   	{
      position: new google.maps.LatLng(-33.7973, 151.16984),
      type: "info"
    }
  ];

  //Auto adjust zoom based on markers
  var bounds = new google.maps.LatLngBounds();

  for (var i = 0; i < features.length; i++) {
    bounds.extend(features[i].position);
    var marker = new google.maps.Marker({
      position: features[i].position,
      icon: image,
      map: map
    });
  }
  map.fitBounds(bounds);
}
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation."
  );
  infoWindow.open(map);
}
