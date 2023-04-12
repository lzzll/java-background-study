local key = KEYS[1];
local threadId = ARGV[1];
local releaseTime = ARGV[2];

if(redis.call('hexists', key, threadId) == 1) then
    redis.call('expire', key, releaseTime);
    return 1;
end;
return 0;