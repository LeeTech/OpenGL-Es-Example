package com.lee.opengles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lee.opengles.itemdeco.DividerItemDecoration;
import com.lee.opengles.tutorial.Example;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Example> mExampleList;

    private RecyclerView mExampleRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExampleRv = (RecyclerView) findViewById(R.id.exampleRv);
        initData();
    }

    private void initData() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new Example("Example", ExampleActivity.class));
        mExampleList.add(new Example("Square", SquareActivity.class));
        mExampleList.add(new Example("Cube", CubeActivity.class));
        mExampleList.add(new Example("SimpleTexture", SimpleTextureActivity.class));
        mExampleList.add(new Example("Point", DrawPointActivity.class));
        mExampleList.add(new Example("LineSegment", LineSegmentActivity.class));
        mExampleList.add(new Example("Triangles", TrianglesActivity.class));
        mExampleList.add(new Example("Icosahedron", IcosahedronActivity.class));
        mExampleList.add(new Example("Star", StarActivity.class));
        mExampleList.add(new Example("Sphere", SphereActivity.class));
        mExampleList.add(new Example("Lighting", LightingActivity.class));

        mExampleRv.setLayoutManager(new LinearLayoutManager(this));
        mExampleRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mExampleRv.setAdapter(new ExampleAdapter(mExampleList));
    }

    private class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleHolder> {

        private List<Example> mExampleList;

        public ExampleAdapter(List<Example> exampleList) {
            this.mExampleList = exampleList;
        }

        @Override
        public ExampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ExampleHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.example_item_layout, null));
        }

        @Override
        public void onBindViewHolder(ExampleHolder holder, int position) {
            holder.name.setText(mExampleList.get(position).getExampleName());
            holder.name.setOnClickListener(new OnExampleItemClick(mExampleList.get(position).getClazz()));
        }

        @Override
        public int getItemCount() {
            return mExampleList == null ? 0 : mExampleList.size();
        }

        class ExampleHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public ExampleHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.nameTv);
            }
        }

        class OnExampleItemClick implements View.OnClickListener {

            private Class clazz;

            public OnExampleItemClick(Class clazz) {
                this.clazz = clazz;
            }

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, clazz));
            }
        }
    }
}
