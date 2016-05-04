package com.maotong.weibo.main.moviedetail;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.maotong.weibo.R;

public class CommentFragment extends DialogFragment {

    private EditText mComment;

    public interface CommentListener
    {
        void onCommentComplete(String comment);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_comment, null);
        mComment = (EditText) view.findViewById(R.id.id_movie_detail_comment_edit);
        builder.setView(view)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                CommentListener listener = (CommentListener) getActivity();
                                listener.onCommentComplete(mComment
                                        .getText().toString());
                            }
                        }).setNegativeButton("CANCEL", null);
        return builder.create();
    }
}
