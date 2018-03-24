package william1099.com.design2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class FragmentA extends Fragment {
    RecyclerView recyclerView;
    View v;
    Context context;

    RequestQueue mRequestQueue;
    StringRequest stringRequest;
    ArrayList<HashMap<String, String>> datasource;
    HashMap<String, String> map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_a, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleviewA);
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        datasource = new ArrayList<>();
        stringRequest = new StringRequest(Request.Method.GET,"http://rss.detik.com/index.php/hot_celebs" , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                processXML(response);
                myAdapter adapter = new myAdapter(datasource, context, getActivity());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue = mySingleton.getInstance().mRequestQueue;
        mRequestQueue.add(stringRequest);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void getData(ArrayList<HashMap<String, String>> data) {
        this.datasource = data;
        Log.d("s", "test");
    }

    public void processXML(String res) {
        try {
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(res));
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); i++) {
                Element listChild = (Element) list.item(i);
                NodeList listChild2 = listChild.getChildNodes();
                map = new HashMap<>();
                for (int j = 0; j < listChild2.getLength(); j++) {
                    if (listChild2.item(j).getNodeName().equalsIgnoreCase("title")) {
                        map.put("title", listChild2.item(j).getTextContent());
                        //Log.d("a", listChild2.item(j).getTextContent());
                    }
                    if (listChild2.item(j).getNodeName().equalsIgnoreCase("description")) {
                        String desc = listChild2.item(j).getTextContent();
                        int p = desc.indexOf("/>");
                        String desc2 = desc.substring(p + 2);
                        map.put("description", desc2);
                        //   Log.d("a", desc2);
                    }
                    if (listChild2.item(j).getNodeName().equalsIgnoreCase("pubDate")) {
                        map.put("date", listChild2.item(j).getTextContent());
                        // Log.d("a", listChild2.item(j).getTextContent());
                    }
                    if (listChild2.item(j).getNodeName().equalsIgnoreCase("enclosure")) {
                        map.put("image", listChild2.item(j).getAttributes().item(0).getTextContent());
                        //Log.d("a", listChild2.item(j).getAttributes().item(0).getTextContent());
                    }
                    if (listChild2.item(j).getNodeName().equalsIgnoreCase("link")) {
                        map.put("link", listChild2.item(j).getTextContent());
                        // Log.d("a", listChild2.item(j).getTextContent());
                    }
                }
                datasource.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
