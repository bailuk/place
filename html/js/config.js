
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


const initialBounds = {
    west: 8.721067,
    east: 8.733311,
    north: 47.633246,
    south: 47.625047
}




