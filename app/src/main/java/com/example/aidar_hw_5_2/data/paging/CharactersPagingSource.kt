package com.example.aidar_hw_5_2.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aidar_hw_5_2.data.api.ApiService
import com.example.aidar_hw_5_2.data.model.characters.Character

const val START_INDEX = 1

class CharactersPagingSource(
    private val api: ApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: START_INDEX
            val response = (api.getAllCharacters(currentPage).body()?.characters) ?: emptyList()
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = if (response.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }
}