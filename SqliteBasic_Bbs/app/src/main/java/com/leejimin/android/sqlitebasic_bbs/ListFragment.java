package com.leejimin.android.sqlitebasic_bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    CustomAdapter adapter;

    Button btnWrite;
    Button btnSearch;
    EditText etSearch;

    ListView listView;



    //목록에서 사용할 데이터셋 정의
    ArrayList<BbsData> datas = new ArrayList<>();


    int listCount = 10;
    int totalCount = 0;



    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        etSearch = (EditText)view.findViewById(R.id.etSearch);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //trim은 space를 없애줌
                String word = etSearch.getText().toString().trim();
                if(!"".equals(word)){
                    listCount = 10;
                    //setListByWord(listCount, word);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //write 버튼
        btnWrite = (Button) view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.action(MainActivity.ACTION_WRITE);

            }
        });

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new CustomAdapter(inflater);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BbsData data = datas.get(position);

                int bbsno = data.no;
                mListener.actionEdit(bbsno);


            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                //리스트뷰의 스크롤이벤트에서 마지막 아이템을 체크하는 로직
                Log.i("onscroll","totalItemCount="+totalItemCount); //현재 리스트에 있는 아이템의 총 개수
                Log.i("onscroll","firstVisibleItem="+firstVisibleItem); // 현재 리스트상에 보여지는 최상단 아이템의 index
                Log.i("onscroll","visibleItemCount="+visibleItemCount); //현재 화면 내에 조금이라도 보이는 아이템



                if(totalItemCount == firstVisibleItem + visibleItemCount){
                    //현재 리스트에 있는 데이터 개수가 실제 database에 있는 데이터 개수보다 작을때만 리스트를 갱신한다
                    if(totalItemCount < totalCount) {

                        listCount = listCount + 10;

//                        String word = etSearch.getText().toString().trim();
//                        if(!"".equals(word)){
//                           setListByWord(listCount, word);
//                        }else{
//                            setList(listCount);
//                        }
//
//                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });


        return view;
    }


    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            //메인 액티비티가 이 리스너를 구현했는지 확인

        } else { // 구현하지 않았으면 메인액티비티와 통신할 방법이 없으므로 앱을 죽인다
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        setList(10, listCount);


    }



    public void setList(int count, int skip){
        totalCount = DataUtil.selectCount(getContext());
        datas = DataUtil.selectAll(getContext(), count, skip);

    }

//    public void setListByWord(int count, String word){
//        totalCount = DataUtil.selectCountByWord(getContext());
//        datas = DataUtil.selectAllbyWord(getContext(), count,word);
//
//    }
//


    @Override
    public void onDetach() {
        super.onDetach();
    }


    class CustomAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public CustomAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return datas.size();

        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_list_item, null);
            }

            TextView no = (TextView) convertView.findViewById(R.id.textNo);
            TextView title = (TextView) convertView.findViewById(R.id.textTitle);

            no.setText(datas.get(position).no + "");
            title.setText(datas.get(position).title );

            return convertView;
        }


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
