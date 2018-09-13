/*var map = L.map('map');
var lat = 41.683229;
var lng = -0.887100;
map.setView([lat, lng], 17);
L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 22,
    attribution: 'Map data &copy; OpenStreetMap contributors'
}).addTo(map);
var sidebar = L.control.sidebar('sidebar');
sidebar.addTo(map);*/

var map = L.map('map',{ //CRS por defecto EPSG3857

    maxZoom: 20,
    minZoom: 18

});
var lat = 41.683229;
var lng = -0.887100;
map.setView([lat, lng], 17);
//Carga todos los layers de OSM y de geoserver
L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 20,
    attribution: 'Map data &copy; OpenStreetMap contributors'
}).addTo(map);
var wmsLayer0= L.tileLayer.wms("http://localhost:8088/geoserver/wms", {
    layers: 'WilleWMS:planta_0',
    format: 'image/png',
    maxZoom: 20,
    transparent: true
}).addTo(map);
var wmsLayer1= L.tileLayer.wms("http://localhost:8088/geoserver/wms", {
    layers: 'WilleWMS:planta_1',
    format: 'image/png',
    maxZoom: 20,
    transparent: true
});
var wmsLayer2= L.tileLayer.wms("http://localhost:8088/geoserver/wms", {
    layers: 'WilleWMS:planta_2',
    format: 'image/png',
    crs:L.CRS.EPSG3857,
    maxZoom: 20,
    transparent: true
});
var wmsLayer3= L.tileLayer.wms("http://localhost:8088/geoserver/wms", {
    layers: 'WilleWMS:planta_3',
    format: 'image/png',
    maxZoom: 20,
    transparent: true
});
var wmsLayerSot= L.tileLayer.wms("http://localhost:8088/geoserver/wms", {
    layers: 'WilleWMS:planta_sotano',
    format: 'image/png',
    maxZoom: 20,
    transparent: true
});
var wmsLayer4= L.tileLayer.wms("http://localhost:8088/geoserver/wms", {
    layers: 'WilleWMS:ada_planta_4',
    format: 'image/png',
    maxZoom: 20,
    transparent: true
});
//Control de las plantas que se muestran


var pisos = {
    "<span id=Sotano>Sotanos</span>":wmsLayerSot,
    "<span id=Plantabaja>Planta baja</span>":wmsLayer0,
    "<span id=Primerpiso>Primer piso</span>":wmsLayer1,
    "<span id=Segundopiso>Segundo piso</span>":wmsLayer2,
    "<span id=Tercerpiso>Tercer piso</span>":wmsLayer3,
    "<span id=Cuartopiso>Cuarto piso (Ada)</span>":wmsLayer4
};


L.control.layers(pisos).addTo(map);
//Barra lateral
var sidebar = L.control.sidebar('sidebar');
sidebar.addTo(map);


////  COMPORTAMIENTO BOTON


var punto;
var infoespacio;
var marker=null;
var puntolat;
var puntolong;
var planta;
var array={}

$(document).ready(function(){

    console.log("voy al registro");
    url="/registro";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", url, false);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
    console.log(xmlhttp.response);

    array=JSON.parse(xmlhttp.response);
    console.log(array);
    todasnotificaciones="";
    for (x=0;x<array.length;x++){
        var colorEstado;
        var estado = array[x]["estado"];
        if(estado==="Pendiente"){
            colorEstado = "\"color:yellow\"";
        }else if(estado==="Aceptada"){
            colorEstado = "\"color:blue\"";
        }else if(estado==="Completada"){
            colorEstado = "\"color:green\"";
        }else if(estado==="Asignada"){
            colorEstado = "\"color:blue\"";
        }else if(estado==="Cancelada"){
            colorEstado = "\"color:red\"";
        }
        contenidoficha='<div id="ficha' + array[x]["id"] + '">'+
            '<b class="w3-center">Incidencia '+x+'</b><br>'+
            '<b>Nombre de la incidencia: '+ array[x]["nombre"] +' </b><br>'+
            '<b>Descripcion: '+ array[x]["descripcion"] +' </b><br>'+
            '<b style='+colorEstado+'>Estado: '+ array[x]["estado"]+' </b><br>'+
            '<b>Planta: '+ array[x]["localizacion"]["planta"]+' </b><br>'+
            '<b>Fecha Creada: ' + array[x]["fechaCreada"]+' </b><br>'+
            '<b>Edificio: ' + array[x]["nombreEdificio"]+' </b><br>'+
            '<b>Id espacio: ' + array[x]["idespacio"]+' </b><br>';
        if(estado==="Pendiente"){
            contenidoficha += '<button id="Cancelar' + array[x]["id"] + '"onclick="actualizarincidencia(this.id)" class="w3-button w3-blue">Cancelar</button>';
            contenidoficha += '<button id="Aceptar' + array[x]["id"] + '"onclick="actualizarincidencia(this.id)" class="w3-button w3-blue">Aceptar</button>';
        }else if(estado==="Aceptada"){
            contenidoficha+= '<button id="Completar' + array[x]["id"] + '"onclick="actualizarincidencia(this.id)" class="w3-button w3-blue">Completada</button>';
        }
        contenidoficha +='</div>' +
            '<br><hr/><hr/>';
        console.log("Vamos por " + array[x]["idespacio"]);
        todasnotificaciones+=contenidoficha
    }
    document.getElementById('nuevasincidencias').innerHTML=todasnotificaciones;

});

function actualizarincidencia(clicked_id) {

    console.log(clicked_id);

    if (clicked_id.includes("Cancelar")){
        console.log("Cancelo");
        var data = {};
        data.id = clicked_id.split("Cancelar")[1];
        data.estado = "Cancelada";
        var json = JSON.stringify(data);
        url="/cancelarinci";
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", url, false);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(json);

        array=JSON.parse(xmlhttp.response);
        window.location.replace(array);
    }
    else if (clicked_id.includes("Aceptar")){
        console.log("Acepto");
        var data = {};
        data.id = clicked_id.split("Aceptar")[1];
        data.estado = "Aceptada";
        var json = JSON.stringify(data);
        url="/aceptarinci";
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", url, false);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(json);

        array=JSON.parse(xmlhttp.response);
        window.location.replace(array);
    }
    else if (clicked_id.includes("Completar")){
        console.log("Completar");
        var data = {};
        data.id = clicked_id.split("Completar")[1];
        data.estado = "Completada";
        var json = JSON.stringify(data);
        url="/completarinci";
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", url, false);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(json);

        array=JSON.parse(xmlhttp.response);
        window.location.replace(array);
    }

}

function logout(){
    url="/logout";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", url, false);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();

    array=JSON.parse(xmlhttp.response);
    window.location.replace(array);
}

function muestraincidencias(idE) {
    map.removeLayer(marker);
    todasnotificaciones=""
    for (x=0;x<array.length;x++){
        if(array[x]["idespacio"]==idE){
            var colorEstado;
            var estado = array[x]["estado"];
            if(estado==="Pendiente"){
                colorEstado = "\"color:yellow\"";
            }else if(estado==="Aceptada"){
                colorEstado = "\"color:blue\"";
            }else if(estado==="Completada"){
                colorEstado = "\"color:green\"";
            }else if(estado==="Asignada"){
                colorEstado = "\"color:blue\"";
            }else if(estado==="Cancelada"){
                colorEstado = "\"color:red\"";
            }
            contenidoficha='<div id="ficha">'+
                '<b class="w3-center">Incidencia '+x+'</b><br>'+
                '<b>Nombre de la incidencia: '+ array[x]["nombre"] +' </b><br>'+
                '<b>Descripcion: '+ array[x]["descripcion"] +' </b><br>'+
                '<b style='+colorEstado+'>Estado: '+ array[x]["estado"]+' </b><br>'+
                '<b>Planta: '+ array[x]["localizacion"]["planta"]+' </b><br>'+
                '<b>Fecha Creada: ' + array[x]["fechaCreada"]+' </b><br>'+
                '</div><br><hr/><hr/>';
            console.log("Vamos por " + array[x]["idespacio"]);
            todasnotificaciones+=contenidoficha
        }

    }
    if(todasnotificaciones==""){
        todasnotificaciones='<div id="ficha"><b class="w3-center">No existen incidencias en este espacio </b><br></div>'
    }
    var popup1 = L.responsivePopup().setContent(todasnotificaciones);
    marker=L.marker(punto.latlng).addTo(map).bindPopup(popup1,{maxHeight: 500});
    marker.openPopup();
}




function getinfoEspacio(e) {
    // localizar el piso

    var x =  document.getElementsByName("leaflet-base-layers");

    if(x[0].checked){
        planta=-1
    }else if(x[1].checked){
        planta=0
    }else if(x[2].checked){
        planta=1
    }else if(x[3].checked){
        planta=2
    }else if(x[4].checked){
        planta=3
    }else if(x[5].checked){
        planta=4
    }

    // transformacion de coordenadas a 25830
    var bngprojection = "+proj=utm +zone=30 +ellps=GRS80 +units=m +no_defs ";
    punto = proj4(bngprojection, [ e.latlng.lng,e.latlng.lat]);
    puntolat=punto[1];
    puntolong=punto[0];
    var data = {};
    data.latitud =  punto[1];
    data.longitud=  punto[0];
    data.planta  = planta;

    var json = JSON.stringify(data);
    url="/espacio";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", url, false);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(json);
    infoespacio=JSON.parse(xmlhttp.responseText);
    punto=e;
    console.log("EL INFO ESPACIO = "+ xmlhttp.responseText)
    idEspacio=infoespacio["idE"]
    var contenidoficha='<div id="ficha">'+
        '<b class="w3-center">Ficha</b><br>'+
        '<b>Edificio: '+infoespacio["edificio"] +' </b><br>'+
        '<b>Espacio: '+ infoespacio["nombre"]+' </b><br>'+
        '<b>Planta: '+ infoespacio["planta"]+' </b><br>'+
        '<b>Exterior:' + infoespacio["exterior"]+' </b><br>'+
        '<button  onclick="muestraincidencias(idEspacio)"  role="button" class="w3-button w3-blue">Ver incidencias</button>'+
        '</div>';
    popup = L.responsivePopup().setContent(contenidoficha);
    marker=L.marker(e.latlng).addTo(map).bindPopup(popup);
    marker.openPopup();
}

function onMapClick(e) {
    if(marker!=null) {
        map.removeLayer(marker);
    }
    getinfoEspacio(e);
    map.flyTo(e.latlng);

}
map.on('click',onMapClick);

