#include <string>
#include <cstdlib>
#include <iomanip>
#include <iostream>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>

#include "Application.h"
#include "UniformDiscrRndGen.h"

using namespace app;
using namespace std;

static const cv::String DEFAULT_WIN_NAME = "Default Image";
static const cv::String MODIFIED_WIN_NAME = "Modified Image (Shape)";

static const unsigned DEFAULT_THRESHOLD = 0U;

static bool isBelowThreshold(const cv::Vec3b& color, unsigned threshold);
static void highlightShapeArea(cv::Mat& image, unsigned threshold);
static int calcReplicationsNum(int rows, int cols);
static double monteCarloSizeRatio(const cv::Mat& image, unsigned threshold);
static void printStats(const cv::Mat& image, unsigned threshold,
	double sizeRatio);
static void usage();

Application::Application(int argc, char** argv) :
	argc_(argc), argv_(argv)
{
}

int Application::run() const
{
	char* inFileName;
	unsigned thredhold;

	if (!processCmdArgs(inFileName, thredhold)) return EXIT_FAILURE;

	cv::Mat image = cv::imread(cv::String(inFileName), cv::IMREAD_COLOR);
	if (!image.data) {
		cerr << "error: could not open or find the image" << endl;
		return EXIT_FAILURE;
	}

	namedWindow(DEFAULT_WIN_NAME, cv::WINDOW_AUTOSIZE);
	imshow(DEFAULT_WIN_NAME, image);

	highlightShapeArea(image, thredhold);

	namedWindow(MODIFIED_WIN_NAME, cv::WINDOW_AUTOSIZE);
	imshow(MODIFIED_WIN_NAME, image);
	cv::waitKey();

	const auto sizeRatio = monteCarloSizeRatio(image, thredhold);

	printStats(image, thredhold, sizeRatio);

	cin.get();

	return EXIT_SUCCESS;
}

bool Application::processCmdArgs(char*& inFileName, unsigned& threshold) const
{
	if (argc_ < 2) {
		cerr << "error: not enough arguments" << endl;
		usage();
		return false;
	}

	inFileName = argv_[1];
	threshold = (argc_ >= 3) ? static_cast<unsigned>(stoi(argv_[2])) :
		DEFAULT_THRESHOLD;

	return true;
}

static bool isBelowThreshold(const cv::Vec3b& color, unsigned threshold)
{
	return (color[0] <= threshold) && (color[1] <= threshold) &&
		(color[2] <= threshold);
}

static void highlightShapeArea(cv::Mat& image, unsigned threshold)
{
	for (int i = 0; i < image.rows; i++) {
		for (int j = 0; j < image.cols; j++) {
			cv::Vec3b& color = image.at<cv::Vec3b>(i, j);

			color[0] = color[1] = color[2] =
				isBelowThreshold(color, threshold) ? 0 : 255;
		}
	}
}

static int calcReplicationsNum(int rows, int cols)
{
	return (rows * cols) + 10000;
}

static double monteCarloSizeRatio(const cv::Mat& image, unsigned threshold)
{
	UniformDiscrRndGen rowGen(0, image.rows - 1);
	UniformDiscrRndGen colGen(0, image.cols - 1);

	int validCount = 0;
	const auto replsNum = calcReplicationsNum(image.rows, image.cols);

	for (int i = 0; i < replsNum; i++) {
		const cv::Vec3b& color = image.at<cv::Vec3b>(rowGen.sample(),
			colGen.sample());

		if (!isBelowThreshold(color, threshold)) validCount++;
	}

	return static_cast<double>(validCount) / static_cast<double>(replsNum);
}

static void printStats(const cv::Mat& image, unsigned threshold,
	double sizeRatio)
{
	cout << "Statistics For Shape Area Approximation\n"
		"\tImage size = " << image.cols << "x" << image.rows << "\n"
		"\tShape size ratio = " << fixed << setprecision(6)
		<< (sizeRatio * 100.0) << "%" << endl;
}

static void usage()
{
	cout << "app <in_file_name> <repls_num> <threshold>\n\n"
		"Options:\n"
		"\t<in_file_name>  path of the picture file\n"
		"\t<threshold>     threshold for the 'black' value\n\n"
		"Example:\n"
		"\tapp input.png 2" << endl;
}