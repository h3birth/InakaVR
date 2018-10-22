package inakavr.app.birth.h3.inakavr.model.entity

data class YoutubeDataAPI(var kind: String, var etag:String, var regionCode: String, var pageInfo: PageInfo, var items: List<Items>)
data class PageInfo(var totalResults: Int, var resultsPerPage: Int)
data class Items(var kind: String, var etag:String, var id: ID, var snippet: Snippet, var channelTitle: String, var liveBroadcastContent: String)
data class ID(var kind: String, var videoId:String)
data class Snippet(var publishedAt: String, var channelId: String, var title: String, var description: String, var thumbnails: Thumbnails)
data class Thumbnails(var default: ThumbnailsData, var medium: ThumbnailsData, var high: ThumbnailsData)
data class ThumbnailsData(var url: String, var width: Int, var height: Int)