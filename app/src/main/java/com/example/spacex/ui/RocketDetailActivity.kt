package com.example.spacex.ui

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacex.adapter.RocketImageAdapter
import com.example.spacex.databinding.ActivityRocketDetailBinding
import com.example.spacex.viewmodel.RocketDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRocketDetailBinding
    private lateinit var viewModel: RocketDetailsViewModel
    private lateinit var rocketImageAdapter: RocketImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RocketDetailsViewModel::class.java]

        var rocketId = intent.getStringExtra("rocketId")

        if (rocketId != null) {
            viewModel.getRocketDetails(rocketId)
        }

        viewModel.observeRocketDetailsData().observe(this, Observer { rocketDetails ->

            val rocketName = "<b>" + rocketDetails.name + "</b>"
            binding.rocketName.text = Html.fromHtml(rocketName)

            val rocketCountry = "<b>Active Status:</b> " + rocketDetails.active
            binding.activeStatus.text = Html.fromHtml(rocketCountry)

            val costPerLaunch = "<b>Cost per launch: </b> " + rocketDetails.cost_per_launch
            binding.costPerLaunch.text = Html.fromHtml(costPerLaunch)

            val successRatePercent = "<b>Success Rate percent: </b> " + rocketDetails.success_rate_pct
            binding.successRatePercent.text = Html.fromHtml(successRatePercent)

            val description = "<b>Description: </b><br/>" + rocketDetails.description
            binding.description.text = Html.fromHtml(description)

            val height =
                "<b>Height: </b> " + rocketDetails.height + "ft/" + rocketDetails.height + "<br/>" +
                        "<b>Diameter: </b> " + rocketDetails.diameter + "ft/" + rocketDetails.diameter
            binding.height.text = Html.fromHtml(height)

            binding.wikipedia.text = rocketDetails.wikipedia

            prepareRecyclerView()

            rocketImageAdapter.setRocketImageList(rocketDetails.flickr_images)

        })

    }

    private fun prepareRecyclerView() {

        rocketImageAdapter = RocketImageAdapter()
        binding.imageRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = rocketImageAdapter
        }

    }
}