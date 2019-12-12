▼ インタレストグラフとは
各ユーザーの、何を好むか、といった詳細から特徴を見出したものどものセット。

エッヂの一方にはユーザーが、もう一方にはトピックがつながっているグラフが出来上がる。
コネクションの重みがトピックに対する affinity となる。
-------------------------------------------------

slido.com
使い方

▼ モバイルで特に強い



Many mobile interest indicators simply don't exist on the web, for example, checkins, location data,
near field communication, etc.

Let's take a close look at what’s involved when building a complete and expressive interest graph platform, and how the resulting graph can effectively optimize ad/reward targeting.

Companies developing interest graph platforms have to overcome the following fundamental challenges.

1. Data Collection
There are two approaches to collecting interest data: explicit and implicit. Companies such as Hunch, Blippy and Pinterest have all attempted to address this by asking users to share interests explicitly, with the incentive that users will get more accurate recommendations in return.

However, this approach has some psychological hurdles. Why would I tell an engine that I like BMWs, only for it to suggest BMW deals to me soon after? And if I mention that I like sports cars, in general, would it suggest cars that are too pricey or too big for my taste? In order to get accurate recommendations, I should be more specific: I like sports cars, but mostly European ones, and only those that are under $60,000.

In other words, the amount of data that I have to volunteer in order to get a decent recommendation or deal is so high that I might as well just search for the product directly.

Amazon does a great job at implicit data collection. The site has all the elements of the equation: your purchase history, product search history and even product correlations (people who bought this also bought that).

Any app where users have to take the time to populate their own interests will invariably have inaccurate or artificial interest profiles.

Facebook encountered this issue with its interest hubs: Most people didn’t take the time to populate their profiles with accurate interests. Even if they did spend the time, they populated the subset of interests that they wanted to project publicly. Google+ Sparks faced the same issues.

Interests have to be inferred from normal app usage, where users have opted in. And the app’s normal usage has to provide strong interest indicators. General social networks such as Facebook and Google+ have the luxury of collecting vast data of various types. Specialized social networks such as Foursquare and Pinterest collect data that is heavily biased towards one signal (checkins in the former, and liking photos that belong to certain interest categories in the latter).

2. Noise Filtration


Every action online is considered a signal, and almost every signal in the digital world has its fair share of noise, though the noise levels and types vary greatly. For example, comments are extremely noisy (LOL, OMG, etc.), as are Likes/+1s when applied to photos or comments etc.

However, Likes/+1 of brand pages, for example, are very reliable interest indicators. Essentially, "noise" becomes valuable, depending on the amount of effort a person puts in. But the degree of correlation varies depending on the person’s behavioral profile.

If you take the time to upload a video of yourself skiing, that’s a strong signal. If you simply Like/+1 someone’s skiing photo, it may be that you like the person, or you like skiing, or you are simply trying to get the attention of the poster in order to start a conversation.

Repeated checkins at restaurants/bars are strong interest indicators as well. And clearly, reward/deal redemptions and purchases are very strong signals.

Algorithms are typically used to detect noise patterns and spammy comments, etc. Similarly, signal strengths have to be analyzed. To give simple examples, uploading sailboat photos every week is a different level of interest than liking a friend's sailing photo once in a while. Similarly, checking in at the same sushi restaurant six times last month sends a very different signal than checking in once every two months.

However, here’s where the complexity arises. One checkin per month may actually be a strong indicator if the person travels frequently. In other words, the signal strengths calibration algorithm has to be customized according to the person’s behavioral patterns and lifestyle.

The beauty is that you don’t have to get it right the first time, since, like any neural network, the engines improve with usage over time.

3. Building the Interest Graph
Even after noise filtration engines have been well-trained and continuously re-calibrated to build an interest profile for a given user, constructing an interest graph for a set of users is still challenging. Essentially, the complexity is in aggregating all the signals to form a coherent and reasonably consistent profile.

4. Platform APIs
In my mind, this is the biggest challenge, and so far, no company has managed to deliver a solution. Clearly, a lot of companies build their own interest graphs for their own user bases.

However, being an interest graph platform means publishing APIs that any app/game can use to personalize ads or commerce to their users. This means that your APIs have to be at such fine granularity that other apps/games can integrate them seamlessly, with no detrimental impact on the user experience.

5. Distribution: Attracting the Apps to Use the APIs
As in any B2B sales cycle, the first few customers are the hardest to acquire. In this case, they are also the most important, since their impact on engine accuracy is very significant.

It’s very clear that interest graphs are at the core of the curated mobile web, and will be a key driver for mobile commerce for years to come. Many apps are already building relatively accurate interest graphs of their user bases. Ultimately, the companies that build scalable interest graph platforms with APIs that map to numerous apps and games will dominate the mobile commerce ecosystem.
-------------------------------------------------
