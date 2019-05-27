package com.ceiec.twmp.tmp.utils.tools;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.text.DecimalFormat;

import static java.lang.Math.exp;

/**
 * @author Ding
 * @version V1.0
 * @Description: tools for convert WGS <---> Web Mercator
 * @create 2019-03-22 10:28
 **/
public class GisTools {

    /*************************************************************************************************************************************
     ** @Description check gps in or out poly
     ** @param: wktPoly
     ** @param: lon
     ** @param: lat
     ** @Return boolean if true, gps in poly  ,else gps out of poly
     ** @Author Ding
     ** @Date 2019/3/22 11:04
     **
     ************************************************************************************************************************************/
    public static boolean gpsInPoly(String wktPoly, String lon, String lat) throws ParseException {
        String wktPoint = gps2WktPoint(lon, lat);
        return pointInPoly(wktPoly, wktPoint);
    }


    /*************************************************************************************************************************************
     ** @Description check point in or out poly
     ** @param: wktPoly
     ** @param: wktPoint
     ** @Return boolean  if true, point in poly  ,else point out of poly
     ** @Author Ding
     ** @Date 2019/3/22 10:48
     **
     ************************************************************************************************************************************/
    private static boolean pointInPoly(String wktPoly, String wktPoint) throws ParseException {
        WKTReader reader = new WKTReader(JTSFactoryFinder.getGeometryFactory());
        Geometry poly = null;
        Geometry point = null;

        point = reader.read(wktPoint);
        poly = reader.read(wktPoly);

        return poly.contains(point);
    }


    /*************************************************************************************************************************************
     ** @Description  update a distance meters to space with buffer
     ** @param: space  e.g.   "10,20 10,30 20,50 30,20 10,20"
     ** @param: distanceMeters
     ** @Return java.lang.String  get wkt poly
     ** @Author Ding
     ** @Date 2019/3/22 10:41
     **
     ************************************************************************************************************************************/
    public static String addLonLatDistanceBuffer(String space, int distanceMeters) throws FactoryException, TransformException, ParseException {
        String wktPoly = space2WktPoly(space);

        WKTReader reader = new WKTReader(JTSFactoryFinder.getGeometryFactory());
        WKTWriter writer = new WKTWriter();
        Geometry poly = null;


        //read string to geometry poly
        poly = reader.read(wktPoly);

        //convert wgs84 to web mercator
        CoordinateReferenceSystem crsTarget = CRS.decode("EPSG:3857");
        MathTransform transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, crsTarget);
        poly = JTS.transform(poly, transform);

        //buffer distance meters
        poly = poly.buffer(distanceMeters);

        //write geometry poly to string
        wktPoly = writer.write(poly);


        wktPoly = wktPoly.substring(10);
        wktPoly = wktPoly.substring(0, wktPoly.length()-2);
        wktPoly = " "+wktPoly;
        String  newPoly ="";

        for(String coor: wktPoly.split(",")){
            newPoly = newPoly +", "+ webMercator2Lonlat(coor.substring(1));
        }

        return newPoly.substring(2);
    }


    /*************************************************************************************************************************************
     ** @Description  convert  Web Mercator to WGS LonLat
     ** @param: mercator   e.g.    20 30
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/22 10:30
     **
     ************************************************************************************************************************************/
    private static String webMercator2Lonlat(String mercator)
    {
        double x = Double.parseDouble(mercator.split(" ")[0])/20037508.34*180;
        double y = Double.parseDouble(mercator.split(" ")[1])/20037508.34*180;
        y= 180/Math.PI*(2*Math.atan(exp(y*Math.PI/180))-Math.PI/2);
        DecimalFormat df = new DecimalFormat("#.00000000");

        return df.format(x)+" "+df.format(y);
    }


    private static String space2WktPoly(String space){
        String wkt = "";

        String[] coordinates = space.split(" ");
        for(String coordinate: coordinates){
            wkt = wkt+", "+coordinate.replace(","," ");
        }

        wkt = "POLYGON (("+ wkt.substring(2)+"))";
        return wkt;
    }


    private static String gps2WktPoint(String lon, String lat){
        return "POINT ("+lon+" "+lat+")";
    }
}
