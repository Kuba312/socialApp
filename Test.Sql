select  p.post, f.user1_id as who_can_see_id, p.user_id as who_added
from friend f
join post p
on p.user_id =
case
when p.user_id = f.user1_id then f.user1_id
when p.user_id = f.user2_id then f.user2_id
else 0
end = 1
where p.visibility = 'ONLY_FRIENDS'
and f.user1_id is not null
union
select  p.post, f.user2_id as who_can_see_id, p.user_id as who_added
from friend f
join post p
on p.user_id =
case
when p.user_id = f.user1_id then f.user1_id
when p.user_id = f.user2_id then f.user2_id
else 0
end = 1
where p.visibility = 'ONLY_FRIENDS'
and f.user2_id is not null;
