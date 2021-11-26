/*
This program uses javascript and axios to connect
to an API to then retrieve information in the API via ID'savePreferences
as quickly as possible without triggering the request limit and without
performing unnecesary queries for ID's that have already been searched for
*/

const axios = require("axios")
const axiosRetry = require("axios-retry")
const arrayChunk = require("array-chunk")

//timer
axiosRetry(axios, {
	retryDelay: axiosRetry.exponentialDelay
})

//global variable for ID info
var GV_DATA=[];
async function fetchPosts(ids){
	let count=0;
	for (let i in ids){
		const id=ids[i];
		
		//checks if ID has already been searched for, if already searched for, will display info already searched for
		//for ID saved in global variable
		if (exists(id)===0)
		{
			if (count>=5)
			{
				//the authorization code would be given via Base64 into the url in this way, however since it seems like no 
				//authorization codes were given, I will use this dummy API I found online to use as an example, searching for
				//IDs in the API
				//get here will wait until next ID can be searched for to put data into array
				let result = await axios.get("https://jsonplaceholder.typicode.com/posts/" + id);
				GV_DATA.push(result.data);
				console.log(result.data);
				count--;
			}else{
				//adds ID info into global variable for info using .get and keeps getting IDs until
				//count reaches 5
				++count;
				axios.get("https://jsonplaceholder.typicode.com/posts/" + id).then((result)=>{
					console.log(result.data);
					GV_DATA.push(result.data);
					--count;
				});
			}
		}
	}
}

//checks if id exists in current array, if exists returns true, displays row, else false
function exists(id)
{
	for (let i in GV_DATA)
	{
		const row=GV_DATA[i];
		if (row.id===id)
		{
			console.log(row);
			return 1;
		}
	}
	return 0;
}

//test function with different IDs displaying a few multiple requests
fetchPosts([1, 2, 3, 4, 5, 6, 7, 8, 4, 2, 10]);

/*
OUTPUT:
{
  userId: 1,
  id: 4,
  title: 'eum et est occaecati',
  body: 'ullam et saepe reiciendis voluptatem adipisci\n' +
    'sit amet autem assumenda provident rerum culpa\n' +
    'quis hic commodi nesciunt rem tenetur doloremque ipsam iure\n' +
    'quis sunt voluptatem rerum illo velit'
}
{
  userId: 1,
  id: 3,
  title: 'ea molestias quasi exercitationem repellat qui ipsa sit aut',
  body: 'et iusto sed quo iure\n' +
    'voluptatem occaecati omnis eligendi aut ad\n' +
    'voluptatem doloribus vel accusantium quis pariatur\n' +
    'molestiae porro eius odio et labore et velit aut'
}
{
  userId: 1,
  id: 2,
  title: 'qui est esse',
  body: 'est rerum tempore vitae\n' +
    'sequi sint nihil reprehenderit dolor beatae ea dolores neque\n' +
    'fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\n' +
    'qui aperiam non debitis possimus qui neque nisi nulla'
}
{
  userId: 1,
  id: 1,
  title: 'sunt aut facere repellat provident occaecati excepturi optio reprehenderit',
  body: 'quia et suscipit\n' +
    'suscipit recusandae consequuntur expedita et cum\n' +
    'reprehenderit molestiae ut ut quas totam\n' +
    'nostrum rerum est autem sunt rem eveniet architecto'
}
{
  userId: 1,
  id: 5,
  title: 'nesciunt quas odio',
  body: 'repudiandae veniam quaerat sunt sed\n' +
    'alias aut fugiat sit autem sed est\n' +
    'voluptatem omnis possimus esse voluptatibus quis\n' +
    'est aut tenetur dolor neque'
}
{
  userId: 1,
  id: 6,
  title: 'dolorem eum magni eos aperiam quia',
  body: 'ut aspernatur corporis harum nihil quis provident sequi\n' +
    'mollitia nobis aliquid molestiae\n' +
    'perspiciatis et ea nemo ab reprehenderit accusantium quas\n' +
    'voluptate dolores velit et doloremque molestiae'
}
{
  userId: 1,
  id: 4,
  title: 'eum et est occaecati',
  body: 'ullam et saepe reiciendis voluptatem adipisci\n' +
    'sit amet autem assumenda provident rerum culpa\n' +
    'quis hic commodi nesciunt rem tenetur doloremque ipsam iure\n' +
    'quis sunt voluptatem rerum illo velit'
}
{
  userId: 1,
  id: 2,
  title: 'qui est esse',
  body: 'est rerum tempore vitae\n' +
    'sequi sint nihil reprehenderit dolor beatae ea dolores neque\n' +
    'fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\n' +
    'qui aperiam non debitis possimus qui neque nisi nulla'
}
{
  userId: 1,
  id: 8,
  title: 'dolorem dolore est ipsam',
  body: 'dignissimos aperiam dolorem qui eum\n' +
    'facilis quibusdam animi sint suscipit qui sint possimus cum\n' +
    'quaerat magni maiores excepturi\n' +
    'ipsam ut commodi dolor voluptatum modi aut vitae'
}
{
  userId: 1,
  id: 7,
  title: 'magnam facilis autem',
  body: 'dolore placeat quibusdam ea quo vitae\n' +
    'magni quis enim qui quis quo nemo aut saepe\n' +
    'quidem repellat excepturi ut quia\n' +
    'sunt ut sequi eos ea sed quas'
}
{
  userId: 1,
  id: 10,
  title: 'optio molestias id quia eum',
  body: 'quo et expedita modi cum officia vel magni\n' +
    'doloribus qui repudiandae\n' +
    'vero nisi sit\n' +
    'quos veniam quod sed accusamus veritatis error'
}

*/
