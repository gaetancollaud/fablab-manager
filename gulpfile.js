"use strict";

let gulp = require('gulp');
let del = require('del');
let runSequence = require('run-sequence');

let extdir = 'src/main/webapp/ext-lib'

gulp.task('all:clean', () => del([extdir]));

gulp.task('all:copy:libs', [], () => gulp
	.src([
		'node_modules/*angular/**/*.js',
		'node_modules/*angular-bootstrap/**/*.js',
		'node_modules/*angular-route/**/*.js',
		'node_modules/*angular-elastic/**/*.js',
		'node_modules/*angular-resource/**/*.js',
		'node_modules/*angular-sanitize/**/*.js',
		'node_modules/*angular-translate/**/*.+(min.js|min.css)',
		'node_modules/*angular-ui-calendar/**/*.js',
		'node_modules/*ui-select/**/*.+(min.js|min.css)',
		'node_modules/*bootstrap/dist/**/*.+(min.js|min.css)',
		'node_modules/*components-font-awesome/**/*.js',
		'node_modules/*fullcalendar/**/*.+(min.js|min.css)',
		'node_modules/*font-awesome/**/*.+(woff|woff2|ttf|min.css)',
		'node_modules/*jquery/dist**/*.js',
		'node_modules/*masonry-layout/**/*.js',
		'node_modules/*moment/**/*.js',
		'node_modules/*moment-range/**/*.js',
		'node_modules/*ng-file-upload/dist/**/*.js',
		'node_modules/*ng-table/**/*.+(min.js|min.css)',
		'node_modules/*toastr/build/**/*.+(min.js|min.css)',
		'node_modules/*wizmarkdown/wizMarkdown/wizMarkdown.js',
	])
	.pipe(gulp.dest(extdir)));

gulp.task('prod:all', (cb) => runSequence(
	'all:clean',
	[
		'all:copy:libs',
	], cb));
