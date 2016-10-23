package com.leejimin.android.sqlitebasic_bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class EditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    Button btnCancel;
    Button btnSave;

    EditText title;
    EditText name;
    EditText contents;

    int bbsno = -1;

    private static final int BBS_INSERT = -1;




    public EditFragment(){


    }


    // Database 에서 bbsno 에 해당하는 레코드를 가져와서 화면에 뿌려준다
    public void setData(int bbsno){
        BbsData data = DataUtil.select(getContext(), bbsno);

        title.setText(data.title);
        name.setText(data.name);
        contents.setText(data.contents);
        this.bbsno = bbsno;
    }

    // 캔슬하거나 저장후에 호출하여 텍스트필드값을 초기화해준다
    public void resetData(){
        title.setText("");
        name.setText("");
        contents.setText("");
        this.bbsno = -1;
    }





    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        btnCancel = (Button)view.findViewById(R.id.btnCancel);
        btnSave = (Button)view.findViewById(R.id.btnSave);

        title = (EditText)view.findViewById(R.id.titleText);
        name = (EditText)view.findViewById(R.id.nameText);
        contents= (EditText)view.findViewById(R.id.contentsText);



        //cancel 버튼
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.action(MainActivity.ACTION_CANCEL);

            }
        });


        //save 버튼
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BbsData data = new BbsData();

                data.no = bbsno;
                data.title = title.getText().toString();
                data.name = name.getText().toString();
                data.contents = contents.getText().toString();

                if (bbsno != BBS_INSERT) {

                    DataUtil.insert(getContext(), data);
                   // mListener.actionSave(new BbsData(), MainActivity.SAVE_INSERT);

                }else{

                    DataUtil.update(getContext(), data);
                   // mListener.actionSave(new BbsData(), MainActivity.SAVE_UPDATE);

                }
                resetData();
                mListener.action(MainActivity.ACTION_GOLIST_WITH_REFRESH);


            }
        });


        return view;
    }






    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//          ///  mListener.onFragmentInteraction(string);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//   public interface OnFragmentInteractionListener {
//       // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
