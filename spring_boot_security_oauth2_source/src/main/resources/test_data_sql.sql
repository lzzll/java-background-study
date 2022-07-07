INSERT INTO `oauth_client_details` (
	`client_id`,
	`resource_ids`,
	`client_secret`,
	`scope`,
	`authorized_grant_types`,
	`web_server_redirect_uri`,
	`authorities`,
	`access_token_validity`,
	`refresh_token_validity`,
	`additional_information`,
	`autoapprove`
)
VALUES
	(
		'lzzll_oauth',
		'resource_api',
		'$2a$10$CYX9OMv0yO8wR8rE19N2fOaXDJondci5uR68k2eQJm50q8ESsDMlC',
		'read, write',
		'client_credentials,implicit,authorization_code,refresh_token,password',
		'http://www.baidu.com',
		NULL,
		NULL,
		NULL,
		NULL,
		'false'
	);
