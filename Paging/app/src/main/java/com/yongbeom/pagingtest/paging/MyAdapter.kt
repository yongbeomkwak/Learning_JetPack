package com.yongbeom.pagingtest.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yongbeom.pagingtest.databinding.ItemLayoutBinding
import com.yongbeom.pagingtest.model.Post

class MyAdapter:PagingDataAdapter<Post, MyAdapter.MyViewHolder>(IMAGE_COMPARATOR) {

    /**
     * PagingDataAdapter<T : Any, VH : RecyclerView.ViewHolder> 를 상속받고,
         DiffUtil.ItemCallback 을 정의해주어야만 한다.
     * */
    inner class MyViewHolder(private val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post : Post){
            Log.d("tst5", "bind: ${post.id} 바인드됨")
            binding.userIdText.text = post.myUserId.toString()
            binding.idText.text = post.id.toString()
            binding.titleText.text = post.title
            binding.bodyText.text = post.body
        }
    }
    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem:Post? = getItem(position) //해당 page data가 들어있음

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            /**
             * areItemsTheSame 는 두 개체가 동일한 항목(ID)을 나타내는 지 확인하기 위해 호출 되고,

            areContentsTheSame 는 두 항목에 동일한 데이터가 있는지 확인하기 위해 호출 된다.
             * */
            override fun areItemsTheSame(oldItem: Post, newItem: Post) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post) =
                oldItem == newItem
        }
    }
}