const HtmlWebpackPlugin = require('html-webpack-plugin'); //installed via npm
const webpack = require('webpack'); //to access built-in plugins
const path = require('path');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const config = {
	entry: {
		// 'vendor-js': './src/main/js/vendors.js',
		'bundle': './src/main/js/bundle.js',
	},
	output: {
		path: path.resolve(__dirname, 'src/main/webapp'),
		filename: '[name]-[hash].min.js'
	},
	module: {
		rules: [
			{
				test: /\.(js)$/,
				use: 'babel-loader',
				exclude: /node_modules/
				// options: {
				// 	"plugins": ["transform-remove-strict-mode"]
				// }
			},
			{
				test: /\.css$/,
				use: ExtractTextPlugin.extract({
					use: 'css-loader'
				})
			},
			{
				test: /\.(png|woff|woff2|eot|ttf|svg)$/,
				loader: 'url-loader?limit=100000'
			},
			{
				test: /\.html$/,
				loader: 'raw-loader'
			},
			// {
			// 	test: /\.html$/,
			// 	loaders: ['ngtemplate?relativeTo=/src/', 'html']
			// }
			// {
			// 	test: /\.html$/,
			// 	loader: 'ngtemplate?relativeTo=' + (path.resolve(__dirname, './app')) + '/!html'
			// }
		]
	},
	plugins: [
		// new webpack.optimize.UglifyJsPlugin(),
		new HtmlWebpackPlugin({template: './src/main/js/index.html'}),
		new ExtractTextPlugin('[name]-[hash].min.css'),
		new CopyWebpackPlugin([
			{
				context: 'src/main/js',
				from: 'components/**/*.html',
				to: '.'
			}
		]),
	]
};

module.exports = config;