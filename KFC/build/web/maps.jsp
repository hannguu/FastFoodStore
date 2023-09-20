<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Open Street Map Example</title>

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
</head>
<body>
    <h1>Open Street Map Example</h1>

    <div id="map" style="height: 400px;"></div>

    <script>
        // Kh?i t?o b?n ?? OpenStreetMap
        var map = L.map('map').setView([16.0544,  108.2022], 13); // ?i?m trung tâm và m?c ?? zoom m?c ??nh
        
        // S? d?ng d?ch v? b?n ?? OpenStreetMap
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        // Thêm ?i?m ?ánh d?u (Marker)
        var marker = L.marker([51.5, -0.09]).addTo(map);
    </script>
</body>
</html>