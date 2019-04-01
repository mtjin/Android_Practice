package com.example.myrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//SingerAdapter 안에 뷰홀더라는 걸 정의하기위해 <SingerAdapter.ViewHolder> 라 해줌
//뷰홀더라고 하는 것은 각각의 아이템을 위한 뷰를 담아주기위한 용도로 사용한다고 생각!
public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {
    Context context;

    //리스트뷰에서는 아이템을 위한 뷰를 보관하는데 이거는 데이터만 보관한다.
    ArrayList<SingerItem> items = new ArrayList<SingerItem>();


    ////////////////////////////////////클릭이벤트처리 관련 사용자 정의(이 코드없으면 그냥 리사이클러뷰 구조)//////////////////////////////////////////////////////////////////////////
    OnItemClickListener listener; //참고로 OnItemClickListener는 기존에 있는것과 동일한 이름인데 그냥 같은 이름으로 내가 정의를 했다. (리스트뷰에서는 이게 자동구현되있어서 OnItemClickListener를 구현안하고 호출해서 클릭시 이벤트를 처리할 수 있음)
    public  static interface  OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    } // 이벤트처리하는게 리스트뷰같은 경우는 setOnItemClickListener() 라고하는게 미리 정의되어있는데 리사이클러뷰는 그게없으니깐 리스트뷰처럼 사용할 수 있게 사용자가 직접구현해줌.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public  SingerAdapter(Context context){
        this.context =  context;
    }

    @Override //아이템개수
    public int getItemCount() {
        return items.size();
    }


    @NonNull
    @Override //뷰홀더가 만들어지는 시점에 호출되는 메소드(각각의 아이템을 위한 뷰홀더 객체가 처음만들어지는시점)
    //만약에 각각의 아이템을 위한 뷰홀더가 재사용될 수 있는 상태라면 호출되지않음 (그래서 편리함, 이건내생각인데 리스트뷰같은경우는 convertView로 컨트롤해줘야하는데 이건 자동으로해줌)
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.singer_item,  viewGroup, false);//viewGroup는 각각의 아이템을 위해서 정의한 xml레이아웃의 최상위 레이아우싱다.

        return new ViewHolder(itemView); //각각의 아이템을 위한 뷰를 담고있는 뷰홀더객체를 반환한다.
    } //이 코드는 사실상 한번이해를 하고나면 그냥 복붙으로 쓰면된다. (i어떤것을 가지고 뷰를 만들고 그거를 뷰홀더에 넣어줄지만 정하면됨)



    //각각의 아이템을 위한 뷰의 xml레이아웃과 서로 뭉쳐지는(결합되는) 경우 자동으로 호출( 즉 뷰홀더가 각각의 아이템을 위한 뷰를 담아주기위한 용도인데 뷰와 아이템이 합질때 호출)
    // Replace the contents of a view //적절한 데이터를 가져와 뷰 소유자의 레이아웃을 채우기 위해 사용
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //뷰홀더라는게 바인딩 될시점
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        SingerItem item = items.get(position); //리사이클러뷰에서 몇번쨰게 지금 보여야되는시점이다 알려주기위해
        viewHolder.setItem(item); //그거를 홀더에넣어서 뷰홀더가 데이터를 알 수 있게되서 뷰홀더에 들어가있는 뷰에다가 데이터 설정할 수 있음


        ////////////////////////////////////클릭이벤트처리 관련 사용자 정의(이 코드없으면 그냥 리사이클러뷰 구조)//////////////////////////////////////////////////////////////////////////
        viewHolder.setOnItemClickListener(listener);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    //아이템을 추가해주고싶을때 이거쓰면됨
    public  void addItem(SingerItem item){
        items.add(item);
    }

    //한꺼번에 추가해주고싶을떄
    public void addItems(ArrayList<SingerItem> items){
        this.items = items;
    }

    //싱어아팀 리턴해주는 메소드
    public  SingerItem getItem(int position){
        return  items.get(position);
    }

    //클릭했을때 동작할 것을 직접 구현해줌 (버튼처럼 setOnClickListenr같은게 리사이클러뷰에는 없다. 직접만들어줘야함)
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    //뷰홀더
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        OnItemClickListener listenr; //클릭이벤트처리관련 변수

        public ViewHolder(@NonNull final View itemView) { //뷰홀더는 각각의 아이템을 위한 뷰를 담고있는데 여기서 그 뷰가 전달이되는것이다
            //예를들어 예제에서 SingerItem.xml에서 textView, textView2라고 하는 id를 가진 두개가 아이템뷰에 포함이되있는데
            //그게 여기 매개변인 itemView안에 들어가있다. 그래서 이것을 가지고 우리가 필요한 작업을 할 수 있다.
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);


            ////////////////////////////////////클릭이벤트처리 관련 사용자 정의(이 코드없으면 그냥 리사이클러뷰 구조)//////////////////////////////////////////////////////////////////////////
            //각각의 아이템뷰가 클릭됬을시 이벤트발생, 이때 어댑터에 등록한 OnItemClickListener 쪽으로 넘겨주게한다.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listenr != null ){
                        listenr.onItemClick(ViewHolder.this, itemView, position);
                    }
                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        public void setItem(SingerItem item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }


        ////////////////////////////////////클릭이벤트처리 관련 사용자 정의(이 코드없으면 그냥 리사이클러뷰 구조)//////////////////////////////////////////////////////////////////////////
        public void setOnItemClickListener(OnItemClickListener listenr){
            this.listenr = listenr;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}
