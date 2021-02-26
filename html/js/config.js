
let baseUrl='http://localhost:8080/php/place/php/rest';





let options = L.Icon.Default.prototype.options;



let markers = [
    {
        iconUrl: 'html/images/marker-icon-blue.png',
        iconSize: options.iconSize,
        iconAnchor: options.iconAnchor,
        popupAnchor: options.popupAnchor,
        shadowUrl: 'html/images/marker-shadow.png',
        shadowSize: options.shadowSize,
        shadowAnchor: options.shadowAnchor

    },
    {
        iconUrl: 'html/images/marker-icon-green.png',
        iconSize: options.iconSize,
        iconAnchor: options.iconAnchor,
        popupAnchor: options.popupAnchor,
        shadowUrl: 'html/images/marker-shadow.png',
        shadowSize: options.shadowSize,
        shadowAnchor: options.shadowAnchor

    },
    {
        iconUrl: 'html/images/marker-icon-pink.png',
        iconSize: options.iconSize,
        iconAnchor: options.iconAnchor,
        popupAnchor: options.popupAnchor,
        shadowUrl: 'html/images/marker-shadow.png',
        shadowSize: options.shadowSize,
        shadowAnchor: options.shadowAnchor

    },
    {
        iconUrl: 'html/images/marker-icon-red.png',
        iconSize: options.iconSize,
        iconAnchor: options.iconAnchor,
        popupAnchor: options.popupAnchor,
        shadowUrl: 'html/images/marker-shadow.png',
        shadowSize: options.shadowSize,
        shadowAnchor: options.shadowAnchor

    }
];


function getSaveMarker(index) {
    index = parseInt(index);
    if ( isNaN(index) || index  < 0 || index >= markers.length) {
        index=0;
    }
    return index;
}
