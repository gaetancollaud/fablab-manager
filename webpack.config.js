const HtmlWebpackPlugin = require('html-webpack-plugin'); //installed via npm
const webpack = require('webpack'); //to access built-in plugins
const path = require('path');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const config = {
	entry: {
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
			}
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
			},
			{
				context: 'src/main/js',
				from: 'lib/**/*.html',
				to: '.'
			}
		]),
	]
};

module.exports = config;