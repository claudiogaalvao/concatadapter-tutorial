package br.com.claudiogalvao.myconcatadapterrecyclerviewtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.ConcatAdapter
import br.com.claudiogalvao.myconcatadapterrecyclerviewtutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var programmingLanguagesAdapter: ProgrammingLanguagesAdapter
    private lateinit var tipsAdapter: TipsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        programmingLanguagesAdapter = ProgrammingLanguagesAdapter()
        tipsAdapter = TipsAdapter().apply {
            gotItItemClickListener = {
                dismissTip()
            }
        }

        binding.recyclerFeed.adapter = ConcatAdapter(tipsAdapter, programmingLanguagesAdapter)

        programmingLanguagesAdapter.submitList(programmingLanguages)
        tipsAdapter.submitList(tips)
    }

    private fun dismissTip() {
        tipsAdapter.notifyItemRemoved(0)
    }
}