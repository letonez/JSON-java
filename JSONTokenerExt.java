package org.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

/**
 * A JSONTokenerExt is an extension of a JSONTokener that supports
 * a user-specified JSONObject BackingMapFactory.  This factory allows
 * creation of JSONObjects that are backed by any Map type.
 * @author JSON.org
 * @version 2018-03-20
 */
public class JSONTokenerExt extends JSONTokener {

    public static interface JSONObjectBackingMapFactory {
        Map<String, Object> createBackingMap();
    }

    /** a Backing Map Factory for new JSONObjects */
    private JSONObjectBackingMapFactory factory = null;


    /**
     * Construct a JSONTokenerExt from a Reader 
     * and a JSONObject BackingMapFactory.
     * The caller must close the Reader.
     *
     * @param reader     A reader.
     */
    public JSONTokenerExt(Reader reader, JSONObjectBackingMapFactory factory) {
        super(reader);
        this.factory = factory;
    }


    /**
     * Construct a JSONTokenerExt from an InputStream
     * and a JSONObject BackingMapFactory.
     * The caller must close the input stream.
     * @param inputStream The source.
     */
    public JSONTokenerExt(InputStream inputStream, JSONObjectBackingMapFactory factory) {
        this(new InputStreamReader(inputStream), factory);
    }


    /**
     * Construct a JSONTokenerExt from a string
     * and a JSONObject BackingMapFactory
     *
     * @param s     A source string.
     */
    public JSONTokenerExt(String s, JSONObjectBackingMapFactory factory) {
        super(new StringReader(s));
        this.factory = factory;
    }


    /**
     * Allow JSONObject to query our BackingMapFactory.
     */
    protected Map<String, Object> createJSONObjectBackingMap() {
        return factory == null ? new HashMap<>() : factory.createBackingMap();
    }

}
